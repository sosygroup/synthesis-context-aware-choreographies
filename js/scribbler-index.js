$(document).ready(function() {
   hljs.initHighlightingOnLoad();

   if ($(".demo").length) {
      var i = 0;
      var txt = `# clone the 'choreography-synthesis-enactment' repository

$ git clone https://github.com/sesygroup/choreography-synthesis-enactment.git choreography-synthesis-enactment
$ cd choreography-synthesis-enactment

# the repository is structured as follow:

choreography-synthesis-enactment
| pom.xml 'build all the projects'
|
+-design-synthesis
|   |
|   +---choreography
|   +---choreography-architecture
|   +---choreography-deployment
|   +---coordination-delegates
|   +---existing-services
|
+-deployment-enactment
|
+-execution-monitoring
|    |
|    +---choreography-instance-execution
|    +---client-monitor
|    +---execution-results 'extracted monitoring data (n.b. use the monitor to show the extracted data)'
|    +---retrieve-monitoring-data
|    +---syncope 'is the monitor (n.b. read the documentation before using it)'
|_

# biuld everything

$ mvn clean install 

            `;
      var speed = 30;

      function typeItOut() {
         if (i < txt.length) {
            $(".demo").append(txt.charAt(i));
            i++;
            setTimeout(typeItOut, speed);
         }
      }

      setTimeout(typeItOut, 1800);
   }

   // responsive navigation
   $(".toggle").on("click", function() {
      if ($('.menu').hasClass("responsive")) {
         $('.menu').removeClass("responsive");
         $(".toggle").removeClass("open");
      } else {
         $('.menu').addClass("responsive");
         $(".toggle").addClass("open");
      }
   });
});