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
class ErrorPagesFixGrailsPlugin {
	def title = "Error Pages Fix"
	def description = "Fixes problems with error handling pages in Grails 1.3 - See GRAILS-6992"

	def author = "Luke Daley"
	def authorEmail = "ld@ldaley.com"
	
	def version = "0.2"
	def grailsVersion = "1.3.* > *" // no higher than 1.3.x
	def dependsOn = [:]
	def loadAfter = ["controllers", "urlMappings"]
	def pluginExcludes = ["grails-app/views/error.gsp"]

	def documentation = "http://grails.org/plugin/error-pages-fix"
}
