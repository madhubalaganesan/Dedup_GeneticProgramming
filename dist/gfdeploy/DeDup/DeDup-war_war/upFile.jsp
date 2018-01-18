<%-- 
    Document   : upFile
    Created on : Jan 15, 2014, 11:18:45 PM
    Author     : Nisha
--%>

<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="java.util.*, java.io.*" %>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>

		<!-- Basic -->
		<meta charset="utf-8">
		<title>Nimbus Drive</title>
		<meta name="keywords" content="HTML5 Template" />
		<meta name="description" content="Nimbus Drive Cloud2020 Project">
		<meta name="author" content="ititans">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Libs CSS -->
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/fonts/font-awesome/css/font-awesome.css">
		<link rel="stylesheet" href="vendor/flexslider/flexslider.css" media="screen" />
		<link rel="stylesheet" href="vendor/magnific-popup/magnific-popup.css" media="screen" />

		<!-- Theme CSS -->
		<link rel="stylesheet" href="css/theme.css">
		<link rel="stylesheet" href="css/theme-elements.css">
		<link rel="stylesheet" href="css/theme-animate.css">

		<!-- Custom CSS -->
		<link rel="stylesheet" href="css/custom.css">

		<!-- Responsive CSS -->
		<link rel="stylesheet" href="css/theme-responsive.css" />

		<!-- Head Libs -->
		<script src="vendor/modernizr.js"></script>

                       <script language="javascript" type="text/javascript">
            function choose(path){
        url = "copy_file";        
        //alert(path);
        if(path == "No"){
            url = "file list.jsp";
            self.location = url
        }else{
           // alert("else part");
            url = "copy_file";
            self.location = url;
        }
//        else if(confirm("Do you like to request the provider "+str+"?")) {
//              url 
//              self.location = url;
//        }else {
//            alert("You are not willing to request the provider");
//        }
            }
        </script>
 
		<!--[if IE]>
			<link rel="stylesheet" href="css/ie.css">
		<![endif]-->

		<!--[if lte IE 8]>
			<script src="vendor/respond.js"></script>
		<![endif]-->

	</head>
	<body >

		<div class="body">
						<header>
				<div class="container">
					<h1 class="logo">
						<a href="index.html">
							<img alt="Nimbus Drive" src="img/logo.png">
						</a>
					</h1>
					<nav>
						<ul class="nav nav-pills nav-top">
							<li>
								<a href="http://www.it.pec.edu"><i class="icon icon-angle-right"></i>About Us</a>
							</li>
							<li>
								<a href="http://www.it.pec.edu"><i class="icon icon-angle-right"></i>Contact Us</a>
							</li>
						</ul>
					</nav>
					<button class="btn btn-responsive-nav btn-inverse" data-toggle="collapse" data-target=".nav-main-collapse">
						<i class="icon icon-bars"></i>
					</button>
				</div>
				<div class="navbar-collapse nav-main-collapse collapse">
					<div class="container">
					
						<nav class="nav-main mega-menu">
							<ul class="nav nav-pills nav-main" id="mainMenu">
								
								<li>
									<a href="file list.jsp">List Files</a>
								</li><li>
									<a href="upload.jsp">Upload</a>
								</li>
                                                                <li>
									<a href="index.jsp">Logout</a>
									
								</li>
							</ul>
						</nav>
					</div>
				</div>
			</header>

			<div role="main" class="main">

				<section class="page-top">
					<div class="container">
						<div class="row">
						</div>
						<div class="row">
							<div class="col-md-12">
								<h2>Similar Match Exists</h2>
							</div>
						</div>
					</div>
				</section>

			
				

				<div class="container">

					<div class="row">
						<div class="col-md-6">

							<!-- <div class="alert alert-success hidden" id="contactSuccess">
								<strong>Success!</strong> Your message has been sent to us.
							</div>

							<div class="alert alert-error hidden" id="contactError">
								<strong>Error!</strong> There was an error sending your message.
							</div>

							<h2 class="short"><strong>Contact</strong> Us</h2>-->
	
                                                        
                                                        <jsp:useBean id="user" scope="page" class="DeDuplication.User" />
                                                        <%
String path = "";
try
{
String ImageFile="";
String itemName = "";
boolean isMultipart = ServletFileUpload.isMultipartContent(request);
if (!isMultipart)
{
}
else
{
FileItemFactory factory = new DiskFileItemFactory();
ServletFileUpload upload = new ServletFileUpload(factory);
List items = null;
try
{
items = upload.parseRequest(request);
}
catch (FileUploadException e)
{
e.getMessage();
}
 
Iterator itr = items.iterator();
while (itr.hasNext())
{
FileItem item = (FileItem) itr.next();
if (item.isFormField())
{
String name = item.getFieldName();
String value = item.getString();
if(name.equals("ImageFile"))
{
ImageFile=value;
}
 
}
else
{
try
{
itemName = item.getName();
//path = "WebPDF\\images\\"+itemName;
//path = (config.getServletContext().getRealPath("/")+"images\\logos\\"+itemName);
path = "D:\\Temp Files\\"+itemName;
File savedFile = new File(path);
System.err.println(path);
item.write(savedFile);
out.println("<form action=\"copy_file\" method=\"post\">");
session.setAttribute("in_path", path);
//out.println(path);
String user_id = session.getAttribute("u_id").toString();
String result = user.compAlg(path, user_id);
if(result.equalsIgnoreCase("Success")){
    response.sendRedirect("file list.jsp");
    out.println("success");
}else{
   // out.println(result);
    //out.println("<script type=\"text/javascript\">");
    session.setAttribute("path", result);
    
    out.println("<input type=\"button\" value=\"Upload Copy\" onclick=\"choose('"+result+"');\" class=\"btn btn-primary btn-lg\"/>");
    out.println("<input type=\"button\" value=\"Dont Upload\" onclick=\"choose('No');\" class=\"btn btn-primary btn-lg\"/>");
    //out.println("if(confirm(\"Do you like to request the provider?\")) { self.location = url; }else {alert(\"You are not willing to request the provider\"); }");    out.println("</script>");
}
out.println("</form>");
//String p_id = session.getAttribute("p_id").toString();
//String f_path = "images\\logos\\"+itemName;

//String sql = "update cloud_provider set logo='"+f_path+"' where p_id='"+p_id+"'";
//boolean result = user.executeState(sql);
//if(result){
//    response.sendRedirect("home.jsp");
//}else{
//    out.println("Error");
//}
}
catch (Exception e)
{
out.println("Error  "+e.getMessage());
}
}
}
}
}
catch (Exception e){
out.println(e.getMessage());
}
%>
                                                </div>
						<div class="col-md-6">

							<h4 class="pull-top">Welcome to <strong>Nimbus Drive</strong></h4>
							<p>Here is the list of documents you have! Please click on Download for downloading the documents...</p>

							<hr />

							

						</div>

					</div>

				</div>

			</div>

			<!--<section class="call-to-action featured footer">
				<div class="container">
					<div class="row">
						<div class="center">
							<h3>Porto is <strong>everything</strong> you need to create an <strong>awesome</strong> website! <a href="http://themeforest.net/item/porto-responsive-html5-template/4106987" target="_blank" class="btn btn-lg btn-primary" data-appear-animation="bounceIn">Buy Now!</a> <span class="arrow hlb" data-appear-animation="rotateInUpLeft" style="top: -22px;"></span></h3>
						</div>
					</div>
				</div>
			</section>-->
	</div>

	<footer id="footer">
		<div class="container">
					<div class="row">
						<div class="footer-ribon">
							<span>UNISYS CLOUD2020</span>
						</div>
						
				<div class="footer-copyright">
					<div class="container">
						<div class="row">
							<div class="col-md-1">
								<a href="index.html" class="logo">
									<img alt="Nimbus Drive" class="img-responsive" src="img/logo-footer.png">
								</a>
							</div>
							<div class="col-md-7">
								<p>� Copyright 2014 by ITitans. All Rights Reserved.</p>
							</div>
						</div>
					</div>
				</div>
			</footer>
		</div>

		<!-- Libs -->
		<script src="../../../ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script>window.jQuery || document.write('<script src="vendor/jquery.js"><\/script>')</script>
		<script src="js/plugins.js"></script>
		<script src="vendor/jquery.easing.js"></script>
		<script src="vendor/jquery.appear.js"></script>
		<script src="vendor/jquery.cookie.js"></script>
		<script src="master/style-switcher/style.switcher.js"></script> <!-- Style Switcher -->
		<script src="vendor/bootstrap.js"></script>
		<script src="vendor/twitterjs/twitter.js"></script>
		<script src="vendor/flexslider/jquery.flexslider.js"></script>
		<script src="vendor/jflickrfeed/jflickrfeed.js"></script>
		<script src="vendor/magnific-popup/magnific-popup.js"></script>
		<script src="vendor/jquery.validate.js"></script>

		<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
		<script src="vendor/jquery.gmap.js"></script>

		<script>

			/*
			Map Settings

				Find the Latitude and Longitude of your address:
					- http://universimmedia.pagesperso-orange.fr/geo/loc.htm
					- http://www.findlatitudeandlongitude.com/find-address-from-latitude-and-longitude/

			*/

			// Map Markers
			var mapMarkers = [{
				address: "217 Summit Boulevard, Birmingham, AL 35243",
				html: "<strong>Alabama Office</strong><br>217 Summit Boulevard, Birmingham, AL 35243<br><br><a href='#' onclick='mapCenterAt({latitude: 33.44792, longitude: -86.72963, zoom: 16}, event)'>[+] zoom here</a>",
				icon: {
					image: "img/pin.png",
					iconsize: [26, 46],
					iconanchor: [12, 46]
				}
			},{
				address: "645 E. Shaw Avenue, Fresno, CA 93710",
				html: "<strong>California Office</strong><br>645 E. Shaw Avenue, Fresno, CA 93710<br><br><a href='#' onclick='mapCenterAt({latitude: 36.80948, longitude: -119.77598, zoom: 16}, event)'>[+] zoom here</a>",
				icon: {
					image: "img/pin.png",
					iconsize: [26, 46],
					iconanchor: [12, 46]
				}
			},{
				address: "New York, NY 10017",
				html: "<strong>New York Office</strong><br>New York, NY 10017<br><br><a href='#' onclick='mapCenterAt({latitude: 40.75198, longitude: -73.96978, zoom: 16}, event)'>[+] zoom here</a>",
				icon: {
					image: "img/pin.png",
					iconsize: [26, 46],
					iconanchor: [12, 46]
				}
			}];

			// Map Initial Location
			var initLatitude = 37.09024;
			var initLongitude = -95.71289;

			// Map Extended Settings
			var mapSettings = {
				controls: {
					panControl: true,
					zoomControl: true,
					mapTypeControl: true,
					scaleControl: true,
					streetViewControl: true,
					overviewMapControl: true
				},
				scrollwheel: false,
				markers: mapMarkers,
				latitude: initLatitude,
				longitude: initLongitude,
				zoom: 5
			};

			var map = $("#googlemaps").gMap(mapSettings);

			// Map Center At
			var mapCenterAt = function(options, e) {
				e.preventDefault();
				$("#googlemaps").gMap("centerAt", options);
			}

		</script>

		<!-- Page Scripts -->
		<script src="js/views/view.contact.js"></script>

		<!-- Theme Initializer -->
		<script src="js/theme.js"></script>

		<!-- Custom JS -->
		<script src="js/custom.js"></script>

		<!-- Google Analytics: Change UA-XXXXX-X to be your site's ID. Go to http://www.google.com/analytics/ for more information. -->
		<script type="text/javascript">

			var _gaq = _gaq || [];
			_gaq.push(['_setAccount', 'UA-42715764-3']);
			_gaq.push(['_trackPageview']);

			(function() {
			var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
			})();

		</script>

	</body>
</html>
