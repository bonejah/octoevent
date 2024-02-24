package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.octoevent.OctApplication;
import com.octoevent.models.Commit;
import com.octoevent.repositories.CommitEventRepository;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OctApplication.class)
public class OctoEventControllerTest {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    CommitEventRepository repository;

    @Test
    public void testAnIssueNumberInexistentThenReturnsNull() {
        HttpEntity<Commit> entity = new HttpEntity<Commit>(null, headers);
        ResponseEntity<Commit> response = restTemplate.exchange("http://localhost:4000/issues/7/events", HttpMethod.GET,
                entity, Commit.class);
        System.out.println("Response: " + response.getBody());
        Assert.assertEquals(null, response.getBody());
    }

    @Test
    public void testAnIssueNumberExistentThenReturnIssue() throws Exception {
        addAnIssueOnDB();
        HttpEntity<Commit> entity = new HttpEntity<Commit>(null, headers);
        ResponseEntity<Commit> response = restTemplate.exchange("http://localhost:4000/issues/4/events", HttpMethod.GET,
                entity, Commit.class);
        System.out.println("Response: " + response.getBody());
        Commit issue = response.getBody();
        Assert.assertEquals(new Long(4), issue.getNumber());
    }

    public void addAnIssueOnDB() throws Exception {
        String json = "{\r\n" +
                "	\"url\": \"https://api.github.com/repos/bonejah/parser/issues/4\",\r\n" +
                "	\"repository_url\": \"https://api.github.com/repos/bonejah/parser\",\r\n" +
                "	\"labels_url\": \"https://api.github.com/repos/bonejah/parser/issues/4/labels{/name}\",\r\n" +
                "	\"comments_url\": \"https://api.github.com/repos/bonejah/parser/issues/4/comments\",\r\n" +
                "	\"events_url\": \"https://api.github.com/repos/bonejah/parser/issues/4/events\",\r\n" +
                "	\"html_url\": \"https://github.com/bonejah/parser/issues/4\",\r\n" +
                "	\"id\": 383251943,\r\n" +
                "	\"node_id\": \"MDU6SXNzdWUzODMyNTE5NDM=\",\r\n" +
                "	\"number\": 4,\r\n" +
                "	\"title\": \"ISSUE 4\",\r\n" +
                "	\"user\": {\r\n" +
                "		\"login\": \"bonejah\",\r\n" +
                "		\"id\": 1268796,\r\n" +
                "		\"node_id\": \"MDQ6VXNlcjEyNjg3OTY=\",\r\n" +
                "		\"avatar_url\": \"https://avatars3.githubusercontent.com/u/1268796?v=4\",\r\n" +
                "		\"gravatar_id\": \"\",\r\n" +
                "		\"url\": \"https://api.github.com/users/bonejah\",\r\n" +
                "		\"html_url\": \"https://github.com/bonejah\",\r\n" +
                "		\"followers_url\": \"https://api.github.com/users/bonejah/followers\",\r\n" +
                "		\"following_url\": \"https://api.github.com/users/bonejah/following{/other_user}\",\r\n" +
                "		\"gists_url\": \"https://api.github.com/users/bonejah/gists{/gist_id}\",\r\n" +
                "		\"starred_url\": \"https://api.github.com/users/bonejah/starred{/owner}{/repo}\",\r\n" +
                "		\"subscriptions_url\": \"https://api.github.com/users/bonejah/subscriptions\",\r\n" +
                "		\"organizations_url\": \"https://api.github.com/users/bonejah/orgs\",\r\n" +
                "		\"repos_url\": \"https://api.github.com/users/bonejah/repos\",\r\n" +
                "		\"events_url\": \"https://api.github.com/users/bonejah/events{/privacy}\",\r\n" +
                "		\"received_events_url\": \"https://api.github.com/users/bonejah/received_events\",\r\n" +
                "		\"type\": \"User\",\r\n" +
                "		\"site_admin\": false\r\n" +
                "	},\r\n" +
                "	\"labels\": [{\r\n" +
                "		\"id\": 999671952,\r\n" +
                "		\"node_id\": \"MDU6TGFiZWw5OTk2NzE5NTI=\",\r\n" +
                "		\"url\": \"https://api.github.com/repos/bonejah/parser/labels/enhancement\",\r\n" +
                "		\"name\": \"enhancement\",\r\n" +
                "		\"color\": \"a2eeef\",\r\n" +
                "		\"default\": true\r\n" +
                "	}],\r\n" +
                "	\"state\": \"open\",\r\n" +
                "	\"locked\": false,\r\n" +
                "	\"assignee\": null,\r\n" +
                "	\"assignees\": [],\r\n" +
                "	\"milestone\": null,\r\n" +
                "	\"comments\": 1,\r\n" +
                "	\"created_at\": \"2018-11-21T18:50:54Z\",\r\n" +
                "	\"updated_at\": \"2018-11-22T12:53:33Z\",\r\n" +
                "	\"closed_at\": null,\r\n" +
                "	\"author_association\": \"OWNER\",\r\n" +
                "	\"body\": \"TESTE ISSUE 4\"\r\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();

        Commit issue = mapper.readValue(json, Commit.class);
        System.out.println("Commit: " + issue);
        System.out.println("Adicionando issue...");
        repository.save(issue);
        System.out.println("Commit adicionada");
    }

}
