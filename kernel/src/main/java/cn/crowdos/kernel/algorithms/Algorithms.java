package cn.crowdos.kernel.algorithms;

import cn.crowdos.kernel.resource.Participant;
import cn.crowdos.kernel.resource.Task;

import java.util.List;

public interface Algorithms {
    public List<Participant> getResults(Task task);
}
