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
package eu.learnpad.core.impl.lsm;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.manager.ComponentLookupException;
import org.xwiki.component.manager.ComponentManager;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.core.rest.RestResource;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.lsm.BridgeInterface;
import eu.learnpad.lsm.Controller;

/*
 * It is not clear yet who is responsible for the instantiation
 * of this class. From what I read from Jean it is supposed to be done
 * automatically when registering the Java component into the XWiki Platform.
 * Thus I do not know how may instances we may actually have. Thus, for the
 * moment I marked it as Singleton.
 *  
 */
@Component
@Named("eu.learnpad.core.impl.lsm.XwikiController")
@Singleton
@Path("/learnpad/lsm/corefacade")
public class XwikiController extends Controller implements XWikiRestComponent, Initializable
{
    @Inject
    private ComponentManager componentManager;
    /*
     * Note that in this Controller are still missing the references with the others
     */

    @Override
    public void initialize() throws InitializationException
    {
        try {
            this.bridge = this.componentManager.getInstance(RestResource.class, "lsm");
        } catch (ComponentLookupException e) {
            throw new InitializationException(e.getMessage(), e);
        }
    }
    @Override
    public void notifyLearningSessionStarted(String questionnaireId, String[] emailList) throws LpRestExceptionXWikiImpl
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyLearningSessionCompleted(String questionnaireId, String[] emailList)
        throws LpRestExceptionXWikiImpl
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void importModelSet(String questionnaireId, String[] emailList) throws LpRestExceptionXWikiImpl
    {
        // TODO Auto-generated method stub

    }

}
