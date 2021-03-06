/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.or.rest;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.SimulationData;

public interface SimulationNotification {
	/**
         * Notifies about the start/end of a simulation session.  
         * 
	 * @param modelSetId
	 *            is the modelset ID concerned by the simulation
	 * @param modelId
	 *            is the model ID process concerned by the simulation
	 * @param action
	 *            is one of the following values: started, stopped
	 * @param simulationId
	 *            is the ID of the concerned simulation
	 * @param data
	 *            data, metadata, and user submitted data defining the context for the simulation
	 * @throws LpRestException
	 */
	// <host>/learnpad/or/bridge/{modelsetid}/{modelid}/simulationinstancenotification?action={started|stopped},simulationid=id
	@POST
	@Path("/{modelsetid}/{modelid}/simulationinstancenotification")
	void simulationInstanceNotification(@PathParam("modelsetid") String modelSetId,
			@PathParam("modelid") String modelId,
			@QueryParam("action") String action,
			@QueryParam("simulationid") String simulationId,
            SimulationData data)
			throws LpRestException;
        
	/**
	 * @param modelSetId
	 *            is the modelset ID concerned by the simulation
	 * @param modelId
	 *            is the model ID concerned by the simulation
	 * @param artifactId
	 *            is the ID of the artifact in the model (event, gateway, unit,
	 *            etc.)         * 
	 * @param simulationId
	 *            is the ID of the concerned simulation
	 * @param data
	 *            data and metadata, and user submitted data defining the context for the simulation
	 * @throws LpRestException
	 */
	// <host>/learnpad/or/bridge/{modelsetid}/{modelid}/simulationtaskstart?artifactid=aid,simulationid=id
	@POST
	@Path("/{modelsetid}/{modelid}/simulationtaskstart")
	void simulationTaskStartNotification(@PathParam("modelsetid") String modelSetId,
			@PathParam("modelid") String modelId,
                        @QueryParam("artifactid") String artifactId,
			@QueryParam("simulationid") String simulationId,
            SimulationData data)
			throws LpRestException;    
        
	/**
	 * @param modelSetId
	 *            is the modelset ID concerned by the simulation
	 * @param modelId
	 *            is the model ID concerned by the simulation
	 * @param artifactId
	 *            is the ID of the artifact in the model (event, gateway, unit,
	 *            etc.)         * 
	 * @param simulationId
	 *            is the ID of the concerned simulation
	 * @param data
	 *            data and metadata, and user submitted data defining the context for the simulation session
	 * @throws LpRestException
	 */
	// <host>/learnpad/or/bridge/{modelsetid}/{modelid}/simulationtaskend?artifactid=aid,simulationid=id

	@POST
	@Path("/{modelsetid}/{modelid}/simulationtaskend")
	void simulationTaskEndNotification(@PathParam("modelsetid") String modelSetId,
			@PathParam("modelid") String modelId,
                        @QueryParam("artifactid") String artifactId,
			@QueryParam("simulationid") String simulationId,
                        SimulationData data) 
			throws LpRestException;          
}
