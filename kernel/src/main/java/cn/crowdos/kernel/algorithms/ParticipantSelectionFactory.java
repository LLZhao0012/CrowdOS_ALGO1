package cn.crowdos.kernel.algorithms;

import cn.crowdos.kernel.system.SystemResourceCollection;

public class ParticipantSelectionFactory implements AbstractAlgoFactory{
    protected final SystemResourceCollection resourceCollection;

    public ParticipantSelectionFactory(SystemResourceCollection resourceCollection){
        this.resourceCollection = resourceCollection;
    }
    @Override
    public Algorithms getAlgo() {
        return null;
    }
}
