package cn.crowdos.kernel.algorithms;

import cn.crowdos.kernel.system.SystemResourceCollection;

public class TaskRecommendationFactory implements AbstractAlgoFactory{
    protected final SystemResourceCollection resourceCollection;

    public TaskRecommendationFactory(SystemResourceCollection resourceCollection){
        this.resourceCollection = resourceCollection;
    }
    @Override
    public Algorithms getAlgo() {
        return null;
    }
}
