### graph-api-auth

Library provides simple authentication for [Microsoft Graph Java SDK](https://mvnrepository.com/artifact/com.microsoft.graph/microsoft-graph)

### Usage
<details>
<summary>1. Implement <code>IAuthenticationProvider</code></summary>

```java
import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.http.IHttpRequest;
import lombok.AllArgsConstructor;

import java.util.function.Supplier;

@AllArgsConstructor
public class AuthenticationProvider implements IAuthenticationProvider {

  private final Supplier<String> token;
  private final String bearer;
  private final String authorizationHeader;

  public AuthenticationProvider(Supplier<String> token) {
    this(
        token,
        "Bearer ",
        "Authorization"
    );
  }

  @Override
  public void authenticateRequest(IHttpRequest request) {
    request.addHeader(authorizationHeader, bearer + token.get());
  }
}
```
</details>

<details>
<summary>2. Implement <code>ApplicationCredential</code> or <code>UserCredential</code></summary>

```java
import io.pan.graphapi.authentication.request.credential.ApplicationCredential;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "graph-api")
public class GraphApiCredentials implements ApplicationCredential {

  private final String clientId;
  private final String clientSecret;
  private final List<String> scopes;
  private final String nationalCloud;
  private final String tenant;
  
  @Override
  public String clientId() {
    return clientId;
  }

  @Override
  public String clientSecret() {
    return clientSecret;
  }

  @Override
  public List<String> scopes() {
    return scopes;
  }

  @Override
  public String tenant() {
   return tenant;
  }
    
  @Override
  public String nationalCloud() {
   return nationalCloud;
  }
}
```
</details>

<details>
<summary>3. Create Graph API client passing instance of <code>Credential</code></summary>

```java
IGraphServiceClient client =  GraphServiceClient.builder()
     .authenticationProvider(
         new AuthenticationProvider(
             new TokenCache(credential)
        )
      )
     .buildClient();
```
</details>
