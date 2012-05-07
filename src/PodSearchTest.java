//package pod_search;

import pod_search.model.DigitalPodModel;
import java.net.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PodSearchTest extends junit.framework.TestCase {

    public void testNothing() {
    }
    
    public void testWillAlwaysFail() {
        fail("An error message");
    }

    public void testUrlRetrieval(){
		DigitalPodModel digital = new DigitalPodModel();
		digital.search("country");
		String localUrl = "http://www.digitalpodcast.com//podcastsearchservice/v2b/search/?appid=podsearch&keywords=country";
		assertEquals(localUrl,digital.getSearchUrl().toString());
	}

	public void testUrlResponse(){
		DigitalPodModel digital = new DigitalPodModel();
		digital.search("country");
		String urlResponse = "<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?> <opml version=\"1.1\"><head><title>Digital Podcast Search - country</title><dateCreated>Weds, 13 July 2005 13:55:28 GMT</dateCreated><dateModified>Mon, 07 May 2012 09:17:43 -0400</dateModified><ownerName>Alex Nesbitt</ownerName><ownerEmail>digitalpodcast@gmail.com</ownerEmail><podcastSearch:format>opml</podcastSearch:format><podcastSearch:totalResults>376</podcastSearch:totalResults><podcastSearch:startIndex>0</podcastSearch:startIndex><podcastSearch:itemsPerPage>10</podcastSearch:itemsPerPage></head><body><outline text=\"Country Flashback\" type=\"link\" url=\"http://countryflash.podomatic.com/rss2.xml\"/><outline text=\"Wolf Mountains Radio\" type=\"link\" url=\"http://wolfmountains.podOmatic.com/rss2.xml\"/><outline text=\"Ben Sorensen's REAL Country\" type=\"link\" url=\"http://realcountry.podomatic.com/rss2.xml\"/><outline text=\"Alt Country Opry Podcast\" type=\"link\" url=\"http://feeds.feedburner.com/AltCountryOpryPodcast\"/><outline text=\"Dave Schmidt's Top Ten Super Country Hits\" type=\"link\" url=\"http://supertop10.podOmatic.com/rss2.xml\"/><outline text=\"CyberCountry: The Video Podcast\" type=\"link\" url=\"http://www.cybercountry.com/vodcast/CyberCountry-TheVideoPodcast.xml\"/><outline text=\"AmericanaOK @ LeithFM\" type=\"link\" url=\"http://americanaok.libsyn.com/rss\"/><outline text=\"Saudi Salutes Country Music\" type=\"link\" url=\"http://saudisalutescountrymusic.mypodcast.com/rss.xml\"/><outline text=\"Brave New Frontiers - progressive country and beyond\" type=\"link\" url=\"http://bnf.libsyn.com/rss\"/><outline text=\"Slinga's Independent Country\" type=\"link\" url=\"http://www.slinga.com/files/podcast.xml\"/></body></opml>";
		assertEquals(urlResponse,digital.getSearchResult());
}
      
    
}