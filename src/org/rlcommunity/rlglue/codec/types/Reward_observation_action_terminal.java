/* 
 * Copyright (C) 2007, Brian Tanner
 * 
http://rl-glue-ext.googlecode.com/

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * 
 *  $Revision$
 *  $Date$
 *  $Author$
 *  $HeadURL$
 * 
 */
package org.rlcommunity.rlglue.codec.types;

/**
 * Composite type for holding reward, observation, action, and terminal.
 * We're trying to move towards not directly accessing the underlying members,
 * which is why we have the getters and setters.
 *
 * @author btanner
 */
public class Reward_observation_action_terminal {

    private double r;
    private Observation o;
    private Action a;
    private int terminal;

    public Reward_observation_action_terminal() {
        r = 0;
        o = new Observation();
        a = new Action();
        terminal = 0;
    }

    public Reward_observation_action_terminal(Reward_observation_action_terminal src) {
        this.r = src.r;
        this.o = src.o.duplicate();
        this.a = src.a.duplicate();
        this.terminal = src.terminal;
    }

    public Reward_observation_action_terminal(double reward, Observation observation, Action action, int terminal) {
        this(reward, observation, action, terminal == 1);
    }

    /**
     * @since 2.0 Want to move towards using terminal as a boolean not an int.
     */
    public Reward_observation_action_terminal(double reward, Observation observation, Action action, boolean terminal) {
        this.r = reward;
        this.o = observation;
        this.a = action;

        if (terminal) {
            this.terminal = 1;
        } else {
            this.terminal = 0;
        }
    }

    /**
     * Set the reward value.
     *
     * @param newReward The new reward to use
     * @since 2.0
     */
    public void setReward(double newReward) {
        this.r = newReward;
    }

    public double getReward() {
        return r;
    }

    /**
     * Adding this method in an effort to get us away from the integer terminal
     * type.  Eventually we can just use the methods and privatize the members.
     *
     * @param newTerminal The new terminal condition.
     * @since 2.0
     */
    public void setTerminal(boolean newTerminal) {
        if (newTerminal) {
            this.terminal = 1;
        } else {
            this.terminal = 0;
        }
    }

    /**
     * Added since i'm cool
     * @param newTerminal The new terminal condition.
     * @since 4.0
     */
    public void setTerminal(int newTerminal) {
        if (terminal != 0 && terminal != 1) {
            throw new IllegalArgumentException("Non valid");
        }
        this.terminal = newTerminal;
    }

    /**
     * @param o The newly observed observation
     * @since 2.0
     */
    public void setObservation(Observation o) {
        this.o = o;
    }

    /**
     * One day we will make the members private and you'll have to use accessors.
     * It would be better if you used the version that returns a boolean, but this  is
     * better than accessing the members directly.
     *
     * @return an integer, 1 if terminal, 0 if not
     * @deprecated use isTerminal
     */
    public int getTerminal() {
        return terminal;
    }

    public Observation getObservation() {
        return o;
    }

    /**
     * Set the action.
     *
     * @param newAction The new action
     * @since 2.0
     */
    public void setAction(Action newAction) {
        this.a = newAction;
    }

    public Action getAction() {
        return a;
    }

    /**
     * @return boolean representation of whether the episode is over.
     * @since 2.0 Want to move towards using terminal as a boolean not an int.
     */
    public boolean isTerminal() {
        return terminal == 1;
    }

    public Reward_observation_action_terminal duplicate() {
        return new Reward_observation_action_terminal(this);
    }
}
