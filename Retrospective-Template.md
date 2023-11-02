TEMPLATE FOR RETROSPECTIVE (Team 12)
=====================================

The retrospective should include _at least_ the following
sections:

- [process measures](#process-measures)
- [quality measures](#quality-measures)
- [general assessment](#assessment)

## PROCESS MEASURES 

### Macro statistics
- 2 stories committed but only basic unit testing was implemented so 2 stories done?
- The point we committed were 13 but since unit testing was missing and the frontend was a bit too simple maybe we were too optimistic
- We planned 8 hour each for the project so a total of 48h and we committed (8h20m 8h30m 8h45m 8h30m 7h 3h)

- Number of stories committed vs. done 
- Total points committed vs. done 
- Nr of hours planned vs. spent (as a team)

**Remember**a story is done ONLY if it fits the Definition of Done:
 
- Unit Tests passing
- Code review completed
- Code present on VCS
- End-to-End tests performed

> Please refine your DoD if required (you cannot remove items!) 

### Detailed statistics

| Story  | # Tasks | Points | Hours est. | Hours actual |  TTE Error   | Average task | Std Estimated |  Std Actual |
|--------|---------|--------|------------|--------------|--------------|--------------|---------------|-------------|
| _#1_   |    4    |    5   |   12h30m   |   12h15m     |     0.02     |       3      |      0.89     |     1.6     |
| _#2_   |    5    |    8   |   14h30m   |   19h50m     |    -0.26     |       4      |      2.2      |     2.8     |

> place technical tasks corresponding to story `#0` and leave out story points (not applicable in this case)

- Hours per task average, standard deviation (estimate and actual)
- Total task estimation error ratio: sum of total hours estimation / sum of total hours spent - 1

  
## QUALITY MEASURES 

- Unit Testing:
  - Total hours estimated: 4h
  - Total hours spent: 3h 30m
  - Nr of automated unit test cases: 15
  - Coverage (if available): *--not calculated--*
- E2E testing:
  - Total hours estimated: --
  - Total hours spent: --
  > Done with Postman and Insomnia, calling the APIs and endpoints, but not estimated nor tracked.
- Code review 
  - Total hours estimated: --
  - Total hours spent: --
> Done together in preparation for the demo.


## ASSESSMENT

**What caused your errors in estimation (if any)?**
  - The main problem was the lack of global view on the project and that we didn't parallelize the workflows 
  - Too optimistic attitude => We need to take into account time for possible errors and bugs
  - We planned the sprint hours (48hrs) first and then tried to fit the task estimations according to the 48hrs, which lead to biased estimates

**What lessons did you learn (both positive and negative) in this sprint?**
  - We learnt that sprint planning is the most important part of the sprint
  - We can achieve a lot when we work together as a team

**Which improvement goals set in the previous retrospective were you able to achieve?**
  
**Which ones you were not able to achieve? Why?**

**Improvement goals for the next sprint and how to achieve them (technical tasks, team coordination, etc.)**
  - Estimation => Plan hours for each task and then look how many we can fit into the sprint 
  - To split the work in multiple parallel flows so that we can start to work at the same time 
  - To have a better holistic view of the project so that everyone can understand every part of it like frontend and backend

**One thing you are proud of as a Team!**
  - That we managed, even if we organized badly, to develop a working lightweight demo to present