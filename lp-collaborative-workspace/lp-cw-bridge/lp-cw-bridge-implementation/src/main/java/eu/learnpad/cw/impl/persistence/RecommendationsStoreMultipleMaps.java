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

package eu.learnpad.cw.impl.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import eu.learnpad.or.rest.data.Recommendations;

/**
*
* @author gulyx
*/
public class RecommendationsStoreMultipleMaps extends RecommendationsStore{
	
	/*
	 * Stores Recommendations by both UserID and SimID 
	 */
	private Map<String, Map<String,Recommendations>> recsByUserIDandSimIDMap;

	public RecommendationsStoreMultipleMaps(){
		this.recsByUserIDandSimIDMap = Collections.synchronizedMap(new HashMap<String, Map<String, Recommendations>>());
	}
	
	
	public void reset() {
		this.recsByUserIDandSimIDMap.clear();
	}

	/*
	 * It returns an instance if the pair (userID,simID)
	 * links to a Recommendations, null otherwise.
	 *
	 * @param userID
	 * @param simID simulation session ID
	 * @returns Recommendations or null  
	 */	
	public Recommendations get(String userID, String simID) {
		Recommendations rec = null;
		Map<String, Recommendations> map = this.recsByUserIDandSimIDMap.get(userID);
		if (map != null)
			rec = map.get(simID);
		
		return rec;
	}

	/*
	 * It returns a map of Recommendations by SimID if the userID
	 * links to instances of Recommendations, null otherwise.
	 *
	 * @param userID
	 * @returns Recommendations Map by SimID or null  
	 */	
	public Map<String,Recommendations> get(String userID) {
		Map<String, Recommendations> map = this.recsByUserIDandSimIDMap.get(userID);
		
		return map;
	}

	/*	
 * Stores the specified Recommendations with the specified pair (userID,simID)
 * Any previously data stored with the same pair is replaced.
 * In this case, the old instance of Recommendations is returned.
 * If there were no previous values stored with the pair, null is returned 
 * 
 * @param userID
 * @param simID simulation session ID
 * @param rec
 * @returns old Recommendations or null  
 */
	public Recommendations put(String userID, String simID, Recommendations rec) {
		Map<String, Recommendations> map = this.recsByUserIDandSimIDMap.get(userID);
		if ( map == null){
			map = Collections.synchronizedMap(new HashMap<String, Recommendations>());
			this.recsByUserIDandSimIDMap.put(userID, map);
		}
		
		Recommendations oldRec = map.put(simID, rec);
		return oldRec ;
	}

	/*
	 * Delete the Recommendations for a pair (userID,simID) if it exists.
	 * Returns the value previously associated the pair, or null. 
	 *
	 * @param userID
	 * @param simID simulation session ID
	 * @returns deleted Recommendations or null  
	 */
	public Recommendations delete(String simID, String userID) {
		Recommendations rec = null;
		
		Map<String, Recommendations> map = this.recsByUserIDandSimIDMap.get(userID);
		if ( map != null){
			rec = map.remove(simID);		
		}
		
		return rec;
	}

	/*
	 * Delete all the Recommendations for a given userID if it exists.
	 * Returns the a map of Recommendations previously associated the pair, or null. 
	 *
	 * @param userID
	 * @param rec
	 * @returns deleted map of Recommendations by SimID or null  
	 */
	public Map<String,Recommendations> delete(String userID) {		
		Map<String, Recommendations> map = this.recsByUserIDandSimIDMap.get(userID);		
		return map;
	}
}
