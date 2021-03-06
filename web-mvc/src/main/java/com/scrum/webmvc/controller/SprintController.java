package com.scrum.webmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/sprint")
public class SprintController {
    @Autowired
    private RestTemplate restTemplate;

  /*  private static final Logger log = Logger.getLogger(SprintController.class);

    private String timeData;
    @Autowired
    private SprintRepository repository;

    @Autowired
    private SprintService sprintService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserService userService;

    public SprintController(SprintRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/sprints", method = RequestMethod.GET)
    public String getsprints(Model model, Principal currentuser) {
        log.info("Request to fetch all sprints from the mongo database");
        Boolean isAdmin = userService.isAdmin(currentuser.getName());

        List<Sprint> sprint_list;
        if (isAdmin)
            sprint_list = repository.findAll();
        else
            sprint_list = repository.findAll().stream().filter(s -> s.isUserInSprint(currentuser)).collect(Collectors.toList());
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("sprints", sprint_list);
        timeData = " username "+ currentuser.getName() + " get sprints  at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
        return "sprint";
    }

    // view actualHours
    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public String printSprint(@RequestParam(value = "sprintid", required = true)
                                          Principal currentuser ,String id, Model model) {
        log.info("Request to open the print sprint form page");
        model.addAttribute("sprintAttr", repository.findById(id).get());
        timeData = " username "+ currentuser.getName() + " print sprint " + id + " at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
        return "printList";
    }

  *//* @RequestMapping(value = "/backlog", method = RequestMethod.GET)
    public String getbacklog(@RequestParam(value="sprintid") String id, Model model,Principal currentuser) {
        log.info("Request to fetch the sprint from the page");
        Sprint backlog = repository.findById(id).get();
        model.addAttribute("backlogAttr", backlog);
        model.addAttribute("sprintid", id);
       timeData = " username "+ currentuser.getName() + " get backlog  at time "+ LocalDateTime.now();
       Initializer.saveTimeData(timeData);
        return "backlog";
    }*//*

    // Opening the add new sprint form page.
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addSprint(@Param(value = "name") String name, Model model, Principal currentuser) {
        log.info("Request to open the new sprint form page");
        Sprint sprint=Sprint.builder().name(name).build();
       // repository.save(sprint);
        model.addAttribute("sprintAttr", sprint);
        timeData = " username "+ currentuser.getName() + " added new sprint " + sprint + " at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
        return "sprintform";
    }

    // Opening the edit sprint form page.
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editSprint(@RequestParam(value="sprintid") String id, Model model, Principal currentuser) {
        log.info("Request to open the edit Sprint form page");
        Boolean isAdmin = userService.isAdmin(currentuser.getName());
        model.addAttribute("sprintAttr", repository.findById(id).get());
        model.addAttribute("isAdmin", isAdmin);
        timeData = " username "+ currentuser.getName() + " edit sprint " + id + " at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
        return "sprintform";
    }

    // view actualHours
    @RequestMapping(value = "/actualHours", method = RequestMethod.GET)
    public String editActualHours(@RequestParam(value = "sprintid", required = true) String id, Model model, Principal currentuser) {
        log.info("Request to open the edit actualHours form page");
        model.addAttribute("sprintAttr", repository.findById(id).get());
        timeData = " username "+ currentuser.getName() + " edit actual hours to sprint " + id + " at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
        return "actualHours";
    }

    // Deleting the specified sprint.
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id") String id, Model model, Principal currentuser) {
        repository.deleteById(id);
        timeData = " username "+ currentuser.getName() + " deleted sprint " + id + " at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
        return "redirect:sprints";
    }

    // Adding a new sprint or updating an existing sprint.
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("sprintAttr") Sprint sprint, Principal currentuser) {                  // needs test for edit or create
       Sprint sprint1;
        if(!sprint.getId().equals("")) {
            sprint.calcDelivery();
            sprint.setTeam( repository.findById(sprint.getId()).get().getTeam());
            repository.save(sprint);
        }
            else {
            if (sprint.getTeam() == null) {
                sprint1 = Sprint.builder().name(sprint.getName()).daily_meeting(sprint.getDaily_meeting()).
                        startSprint(sprint.getStartSprint()).demo(sprint.getDemo()).goal(sprint.getGoal()).plannedPeriod(sprint.getPlannedPeriod())
                        .retrospective(sprint.getRetrospective()).review(sprint.getReview()).tasks(sprint.getTasks()).build();
            } else{ sprint1 = Sprint.builder().name(sprint.getName()).daily_meeting(sprint.getDaily_meeting()).
                    startSprint(sprint.getStartSprint()).demo(sprint.getDemo()).goal(sprint.getGoal()).plannedPeriod(sprint.getPlannedPeriod())
                    .retrospective(sprint.getRetrospective()).review(sprint.getReview()).team(sprint.getTeam()).tasks(sprint.getTasks()).build();
            }
            sprint1.calcDelivery();
            repository.save(sprint1);
        }
        timeData = " username "+ currentuser.getName() + " saved sprint " + sprint + " at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
        return "redirect:sprints";
    }

    //Select one team from teams
    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public String viewTeamToSelect(@RequestParam(value = "id")String id , Model model, Principal currentuser) {
        log.info("Request to fetch all teams from the db for custom team and select team");
        List<Team> teams = teamRepository.findAll();
        teams.removeIf(team -> !team.isActive());
        model.addAttribute("teams",teams);
        model.addAttribute("sprintid", id );
        timeData = " username "+ currentuser.getName() + " view team to select to the sprint  " + id + " at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
        return "sprintteam";
    }

    @RequestMapping(value = "/addteam", method = RequestMethod.GET)
    public String addTeamToSprint(@RequestParam(value = "sprintid") String  sprintid, Principal currentuser,
                                  @RequestParam(value = "teamid") String teamid, Model model) {
        Sprint sprint=repository.findById(sprintid).get();
        sprint.setTeam(teamRepository.findById(teamid).get());
        repository.save(sprint);
        timeData = " username "+ currentuser.getName() + " added team " + teamid+" to sprint "+ sprint + " at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
            return "redirect:/api/sprint/edit?sprintid=" + sprintid;

    }

    @RequestMapping(value = "/sprintcharts", method = RequestMethod.GET)
    public String sprintcharts(@RequestParam(value = "sprintid", required = true) String sprintid
                               , Principal currentuser, ModelMap modelMap) {
        modelMap.addAttribute("sprintid", sprintid);
        timeData = " username "+ currentuser.getName() + " get chart for sprint " + sprintid + " at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
        return "sprintcharts";
    }
    @RequestMapping(value = "/canvasjschart", method = RequestMethod.GET)
    public String canvasjschart(@RequestParam(value = "sprintid", required = true) String sprintid,
                                Principal currentuser , ModelMap modelMap) {
        List<List<Map<Object, Object>>> canvasjsDataList = sprintService.getCanvasjsDataList(sprintid);
        modelMap.addAttribute("dataPointsList", canvasjsDataList);
        modelMap.addAttribute("sprintname", repository.findById(sprintid).get().getName());
        modelMap.addAttribute("teamname", repository.findById(sprintid).get().getTeam().getName());
        timeData = " username "+ currentuser.getName() + " get chart for actual done daily for sprint " + sprintid + " at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
        return "actualdonedaily";
    }

    @RequestMapping(value = "/canvasjschart1", method = RequestMethod.GET)
    public String canvasjschart1(@RequestParam(value = "sprintid", required = true) String sprintid,
                                 Principal currentuser, ModelMap modelMap) {
        List<List<Map<Object, Object>>> canvasjsDataList =sprintService.getCanvasjsDataList1(sprintid);
        modelMap.addAttribute("dataPointsList", canvasjsDataList);
        modelMap.addAttribute("sprintname", repository.findById(sprintid).get().getName());
        modelMap.addAttribute("teamname", repository.findById(sprintid).get().getTeam().getName());
        timeData = " username "+ currentuser.getName() + " get chart for sprint actual remain daily for sprint " + sprintid + " at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
        return "actualremaindaily";
    }
    @RequestMapping(value = "/canvasjschart2", method = RequestMethod.GET)
    public String canvasjschart2(@RequestParam(value = "sprintid", required = true) String sprintid, ModelMap modelMap, Principal currentuser) {
        List<List<Map<Object, Object>>> canvasjsDataList = sprintService.getCanvasjsDataList2(sprintid);
        modelMap.addAttribute("dataPointsList", canvasjsDataList);
        modelMap.addAttribute("sprintname", repository.findById(sprintid).get().getName());
        modelMap.addAttribute("teamname", repository.findById(sprintid).get().getTeam().getName());
        timeData = " username "+ currentuser.getName() + " get chart for sprint developerPerformance " + sprintid + " at time "+ LocalDateTime.now();
        Initializer.saveTimeData(timeData);
        return "developerPerformance";
    }*/
}
