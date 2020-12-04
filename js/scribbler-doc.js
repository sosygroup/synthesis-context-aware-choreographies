$(document).ready(function() {
   hljs.initHighlightingOnLoad();

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

   // scrolling
   $(document).on("scroll", onScroll);

   $('a[href^="#"]').on('click', function(e) {
      e.preventDefault();
      $(document).off("scroll");

      var target = this.hash;
      var $target = $(target);

      $('html, body').stop().animate({
         'scrollTop': $target.offset().top - 20
      }, 600, 'swing', function() {
         $(document).on("scroll", onScroll);
      });

      $(".doc__nav li").removeClass("selected");
      $(this).parent().addClass("selected");

   });

});


function onScroll(event) {
   var scrollPos = $(document).scrollTop();
   $('.doc__nav ul li a').each(function() {
      var currLink = $(this);
      var refElement = $(currLink.attr("href"));
      if (refElement.position().top - 20 <= scrollPos && refElement.position().top - 20 + refElement.height() > scrollPos) {
         $('.doc__nav ul li').removeClass("selected");
         currLink.parent().addClass("selected");
      } else if ($(window).scrollTop() == 0) { // is at the top of the page
         $(".doc__nav li").removeClass("selected")
         $(".doc__nav li:first").addClass("selected");
      } else if ($(window).height() + $(window).scrollTop() == $(document).height()) {  // is at the end of the page
         $(".doc__nav li").removeClass("selected");
         $(".doc__nav li:last").addClass("selected");
      }
   });
}