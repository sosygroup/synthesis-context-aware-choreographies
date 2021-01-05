Automated Synthesis of Context-Aware Choreographies in a Smart City Scenario
=======================
This repository contains the replication package of the paper we submitted to the journal [IEEE Transactions on Software Engineering](https://www.computer.org/csdl/journal/ts) with the title ***Automated Synthesis of Context-Aware Choreographies in a Smart City Scenario***.

Contributors:
 - [Gianluca Filippone (University of L’Aquila)](mailto:gianluca.filippone@graduate.univaq.it)
 - [Marco Autili (University of L’Aquila)](mailto:marco.autili@univaq.it)
 - [Massimo Tivoli (University of L’Aquila)](mailto:massimo.tivoli@univaq.it)

For any information, interested researchers can contact us by writing an email to any contributor listed above. A fully `documentation` can be found [here](https://sosygroup.github.io/synthesis-context-aware-choreographies/).

The replication package can be downloaded [here](https://github.com/sosygroup/synthesis-context-aware-choreographies/archive/main.zip), and it is structured as follows:

```shell 
synthesis-context-aware-choreographies
|   pom.xml 'build all the project'
|
+---choreography
|   +---specification
|   |       Emergency Management System specification.png
|   |       Emergency Management System - Drone Reconnaissance variant v1.png
|   |       Emergency Management System - Drone Reconnaissance variant v2.png
|   |       Emergency Management System - Drone Reconnaissance variant v3.png
|   |
|   \---architecture
|           Emergency Management System architecture.png
|
+---contextevaluator 'implementation of the Context Evaluator'
|
+---coordinationdelegates
|   +---cdBsa
|   +---cdDrone
|   +---cdEarlywarningsystem
|   +---cdEp
|   +---cdSae
|   +---cdShm
|   \---eCD
|
+---monitor 'implementation of the monitor service'
|
+---participant-services
|   +---consumers
|   |   |   pom.xml 'build all the consumer participants of the choreography'
|   |   |
|   |   +---drone
|   |   \---earlywarningsystem
|   |
|   +---prosumers
|   |   |   pom.xml 'build all the prosumer participants of the choreography'
|   |   |
|   |   +---bsa
|   |   +---ep
|   |   +---sae
|   |   \---shm
|   |
|   \---providers
|       |   pom.xml 'build all the provider participants of the choreography'
|       |
|       +---buildingautomation
|       +---cityalarming
|       +---civilprotection
|       +---crowding
|       +---dronefleetcontroller
|       +---energy
|       +---roads
|       +---sensornetwork
|       \---smartevacuation
|
+---sia-endpoints-client 'client application for the set invocation address (n.b., see documentation before using it)'
|
\---sia-endpoints-manager 'endpoints manager (n.b., see documentation before using it)'
```

## License
Licensed under the Apache Software License, Version 2.0.