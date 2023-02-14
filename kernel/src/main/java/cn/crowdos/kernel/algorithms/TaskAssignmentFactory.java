package cn.crowdos.kernel.algorithms;

import cn.crowdos.kernel.system.SystemResourceCollection;

public class TaskAssignmentFactory implements AbstractAlgoFactory{
    protected final SystemResourceCollection resourceCollection;

    public TaskAssignmentFactory(SystemResourceCollection resourceCollection){
        this.resourceCollection = resourceCollection;
    }
    @Override
    public Algorithms getAlgo() {
        return null;
    }
}
