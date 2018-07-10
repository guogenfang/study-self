package observer.pull;

public interface Observer {
	/**
     * 更新接口
     * @param state    更新的状态
     */
    public void update(Subject subject);
}
