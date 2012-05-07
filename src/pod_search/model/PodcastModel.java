//Pod search results model

package pod_search.model;

public class PodcastModel {
	
	private String _name;
	private String _link;
	private int _id;

	public PodcastModel(String name, String link, int id) {
		setName(name);
		setLink(link);
		setID(id);
	}

	public PodcastModel(PodcastModel podcastModel) {
		this(podcastModel.getName(), podcastModel.getLink(), podcastModel.getID());
	}

	public PodcastModel() {
		setName("");
		setLink("");
		setID(-1);
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

	public String getName() {
		return _name;
	}

	public int getID() {
		return _id;
	}

	public String getLink() {
		return _link;
	}

	public String toString () {
		return getID() + " "+ getName();
	}
}