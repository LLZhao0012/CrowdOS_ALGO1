package cn.crowdos.kernel.system.resource;

import cn.crowdos.kernel.algorithms.AbstractAlgoFactory;
import cn.crowdos.kernel.system.SystemResourceHandler;

public class AlgoContainer extends ResourceContainer<AbstractAlgoFactory> {

    public AlgoContainer(AbstractAlgoFactory resource) {
        super(resource);
    }
    @Override
    public SystemResourceHandler<AbstractAlgoFactory> getHandler() {
        return new SystemResourceHandler<AbstractAlgoFactory>() {
            @Override
            public AbstractAlgoFactory getResourceView() {
                return resource;
            }

            @Override
            public AbstractAlgoFactory getResource() {
                return resource;
            }
        };
    }
}
