package org.blue.taskflow.domain.entity.task;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务状态
 * <p/>
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-11-4
 * Time: 15:05:52
 */
public class TaskState {
    // 代码存入地
    private static final HashMap<String, TaskState> MAP = new HashMap<String, TaskState>();

    private static final String CREATE_SIGN = "create";

    private static final String ASSIGN_SIGN = "assign";

    private static final String ACCEPT_SIGN = "accept";

    private static final String REFUSE_SIGN = "refuse";

    private static final String COMPLETE_SIGN = "complete";

    private static final String POSTPONE_SIGN = "postpone";

    private static final String CLOSED_SIGN = "closed";

    public static final TaskState TASK_STATE_CREATE = new TaskState(CREATE_SIGN, "创建");

    public static final TaskState TASK_STATE_ASSIGN = new TaskState(ASSIGN_SIGN, "分配");

    public static final TaskState TASK_STATE_ACCEPT = new TaskState(ACCEPT_SIGN, "接受");

    public static final TaskState TASK_STATE_REFUSE = new TaskState(REFUSE_SIGN, "拒绝");

    public static final TaskState TASK_STATE_COMPLETE = new TaskState(COMPLETE_SIGN, "完成");

    public static final TaskState TASK_STATE_POSTPONE = new TaskState(POSTPONE_SIGN, "延期");

    public static final TaskState TASK_STATE_CLOSED = new TaskState(CLOSED_SIGN, "关闭");

    static {
        MAP.put(TASK_STATE_CREATE.getSign(), TASK_STATE_CREATE);
        MAP.put(TASK_STATE_ASSIGN.getSign(), TASK_STATE_ASSIGN);
        MAP.put(TASK_STATE_ACCEPT.getSign(), TASK_STATE_ACCEPT);
        MAP.put(TASK_STATE_REFUSE.getSign(), TASK_STATE_REFUSE);
        MAP.put(TASK_STATE_COMPLETE.getSign(), TASK_STATE_COMPLETE);
        MAP.put(TASK_STATE_POSTPONE.getSign(), TASK_STATE_POSTPONE);
        MAP.put(TASK_STATE_CLOSED.getSign(), TASK_STATE_CLOSED);
    }

    private String sign;

    private String name;

    private TaskState(String sign, String name) {
        this.sign = sign;
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public String getName() {
        return name;
    }

    /**
     * 得到全部任务状态实例
     *
     * @return 任务状态实例集合
     */
    public static Map getAllTaskState() {
        return MAP;
    }

    /**
     * 得到任务状态实例
     *
     * @param sign 标志
     * @return TaskState 任务状态实例
     */
    public static TaskState getTaskStateBySign(String sign) {
        return MAP.get(sign);
	}
}
