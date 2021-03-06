package support.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;
import support.exception.UnknownTicketException;
import lombok.AllArgsConstructor;
import support.mapper.TicketMapper;
import support.model.ProjectAndTasksResponse;
import support.model.Ticket;
import support.model.TicketCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import support.model.TicketTaskRelation;
import support.repository.TicketRepository;
import support.repository.TicketTaskRelationRepository;
import support.resource.proyect.ProjectResource;
import support.resource.task.TaskResource;
import support.resource.task.model.Task;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketService {

    @Autowired
    private final TicketRepository ticketRepository;
    @Autowired
    private final TicketTaskRelationRepository ticketTaskRelationRepository;
    @Autowired
    private final TaskResource taskResource;
    @Autowired
    private final ProjectResource projectResource;


    @Autowired
    private final TicketMapper ticketMapper;

    public List<Ticket> findAllTickets(){
        return this.ticketRepository.findAll(); //TODO aca debería haber un mapper que devuelva un TicketSummary
    }

    public Ticket findTicketInfo(long ticketId){
        return this.ticketRepository.findById(ticketId).orElseThrow(UnknownTicketException::new);
    }

    public Ticket createTicket(TicketCreationRequest ticketCreationRequest){
        return this.ticketRepository.save(this.ticketMapper.mapToTicket(ticketCreationRequest));
    }

    public List<Ticket> findByName(String name){
        return this.ticketRepository.findByName(name);
    }

    public List<Ticket> findByProductId(Long productId){
        return this.ticketRepository.findByProductId(productId);
    }

    @Transactional
    public void deleteTicketById(long id){
        try {
            this.ticketRepository.deleteById(id);
            this.ticketTaskRelationRepository.deleteByTicketId(id);
        }catch(EmptyResultDataAccessException erdae){
            throw new UnknownTicketException(erdae.getMessage());
        }
    }

    public List<ProjectAndTasksResponse> findTicketTasks(long ticketId){
        Map<Long, List<Task>> proyectIdWithTasks = this.ticketTaskRelationRepository
                .findByTicketId(ticketId).stream()
                .map(ticketTaskRelation -> this.taskResource.getTask(ticketTaskRelation.getTaskId()))
                .collect(Collectors.groupingBy(Task::getProject));

        List<ProjectAndTasksResponse> wantedList = new LinkedList<>();

        List<Tuple2<Long, String>> projectNames =
                proyectIdWithTasks.keySet()
                        .stream()
                        .map(projectId ->
                                Tuples.of(
                                        projectId,
                                        this.projectResource.getProject(projectId).getData().getName()))
                        .collect(Collectors.toList()
                        );

        projectNames.forEach(tuple -> {
            String proyectName = tuple.getT2();
            List<String> projectTasksNames = proyectIdWithTasks.get(tuple.getT1()).stream().map(Task::getName).collect(Collectors.toList());
            wantedList.add(new ProjectAndTasksResponse(proyectName, projectTasksNames));
        });

        return wantedList;
    }

    public void postTicketAndTaskRelation(long ticketId, long taskId) {
        TicketTaskRelation ticketTaskRelation = new TicketTaskRelation();
        ticketTaskRelation.setTicketId(ticketId);
        ticketTaskRelation.setTaskId(taskId);
        if(this.ticketTaskRelationRepository.findByTicketId(ticketId).stream().noneMatch(relation -> relation.getTaskId() == taskId)) {
            this.ticketTaskRelationRepository.save(ticketTaskRelation);
        }
    }
}
