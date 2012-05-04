//Pod search results model

package pod_search.model;

public class PodcastModel {
	
	private String _name;
	private String _link;
	private int _id;

	public PodcastModel(String name, String link, int id = NULL) {
		setName(name);
		setLink(link);
		setID(id);
	}

	public void setName(String name) {
		_name = name;
	}

	public void setLink(String link) {
		_link = link;
	}

	public void setID(int id) {
		_id = id;
	}
}