/*
 * Copyright 2010 Luke Daley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import groovy.xml.StreamingMarkupBuilder

eventWebXmlEnd = { String tmpfile ->
	def xml = new XmlSlurper().parse(webXmlFile)
	
	def errorServlet = xml."**".find { 
		it.'servlet-class'[0].text() == "org.codehaus.groovy.grails.web.servlet.ErrorHandlingServlet"
	}
	if (!errorServlet) {
		throw new IllegalStateException("Could not find error servlet in web xml")
	}
	errorServlet.'servlet-class'[0].replaceBody("grails.plugin.errorpagesfix.PatchedErrorHandlingServlet")


	def sitemeshFilter = xml."filter-mapping".find { it."filter-name" == "sitemesh" }
	if (!sitemeshFilter) {
		throw new RuntimeException("could not find sitemesh filter in xml")
	}
	sitemeshFilter.appendNode { 
		dispatcher("ERROR") 
		dispatcher("REQUEST") 
	}
	
	
	webXmlFile.text = new StreamingMarkupBuilder().bind { 
		mkp.declareNamespace("": "http://java.sun.com/xml/ns/j2ee") 
		mkp.yield(xml) 
	}
}

