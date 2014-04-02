/*
 * Copyright 2008 Brian Tanner
 * http://rl-glue-ext.ext.googlecode.com/
 * brian@tannerpages.com
 * http://brian.tannerpages.com
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.rlcommunity.rlglue.codec.tests;

import org.rlcommunity.rlglue.codec.RLGlue;
import org.rlcommunity.rlglue.codec.types.Observation_action;
import org.rlcommunity.rlglue.codec.types.Reward_observation_action_terminal;

/**
 *
 * @author Brian Tanner
 */
public class Test_Empty_Experiment {
    
    public static int runTest(){
           Glue_Test tester=new Glue_Test("Test_Empty_Experiment");

        RLGlue.RL_init();
        
	for(int whichEpisode=1;whichEpisode<5;whichEpisode++){
		Observation_action startTuple=RLGlue.RL_start();
		
		if(whichEpisode%2==0){
			tester.check_fail(startTuple.getAction().getNumIntegersLength()!=0);
			tester.check_fail(startTuple.getAction().getNumDoublesLength()!=0);
			tester.check_fail(startTuple.getAction().getNumCharsLength()!=0);

			tester.check_fail(startTuple.getObservation().getNumIntegersLength()!=0);
			tester.check_fail(startTuple.getObservation().getNumDoublesLength()!=0);
			tester.check_fail(startTuple.getObservation().getNumCharsLength()!=0);
		}else{
			tester.check_fail(startTuple.getAction().getNumIntegersLength()!=7);
			tester.check_fail(startTuple.getAction().getNumDoublesLength()!=3);
			tester.check_fail(startTuple.getAction().getNumCharsLength()!=1);

			tester.check_fail(startTuple.getObservation().getNumIntegersLength()!=2);
			tester.check_fail(startTuple.getObservation().getNumDoublesLength()!=4);
			tester.check_fail(startTuple.getObservation().getNumCharsLength()!=5);
		}
		
		for(int whichStep=0;whichStep<5;whichStep++){
			Reward_observation_action_terminal stepTuple=RLGlue.RL_step();
			tester.check_fail(stepTuple.getTerminal() !=0);
			tester.check_fail(stepTuple.getReward() != 0);

			if(whichEpisode%2==0){
				tester.check_fail(stepTuple.getAction().getNumIntegersLength()!=0);
				tester.check_fail(stepTuple.getAction().getNumDoublesLength()!=0);
				tester.check_fail(stepTuple.getAction().getNumCharsLength()!=0);

				tester.check_fail(stepTuple.getObservation().getNumIntegersLength()!=0);
				tester.check_fail(stepTuple.getObservation().getNumDoublesLength()!=0);
				tester.check_fail(stepTuple.getObservation().getNumCharsLength()!=0);
			}else{
				tester.check_fail(stepTuple.getAction().getNumIntegersLength()!=7);
				tester.check_fail(stepTuple.getAction().getNumDoublesLength()!=3);
				tester.check_fail(stepTuple.getAction().getNumCharsLength()!=1);

				tester.check_fail(stepTuple.getObservation().getNumIntegersLength()!=2);
				tester.check_fail(stepTuple.getObservation().getNumDoublesLength()!=4);
				tester.check_fail(stepTuple.getObservation().getNumCharsLength()!=5);
			}
			
		}
	}

        
        System.out.println(tester);
        return tester.getFailCount();
    }
    public static void main(String[] args){
       System.exit(runTest());
    }

}
