# MembersApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createMembers**](MembersApi.md#createMembers) | **POST** /members | Create members |


<a id="createMembers"></a>
# **createMembers**
> List&lt;Member&gt; createMembers(createMember)

Create members

Creates one or more new members in the federation. New members start as JUNIOR and need sponsors to become confirmed. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MembersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    MembersApi apiInstance = new MembersApi(defaultClient);
    List<CreateMember> createMember = Arrays.asList(); // List<CreateMember> | 
    try {
      List<Member> result = apiInstance.createMembers(createMember);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembersApi#createMembers");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **createMember** | [**List&lt;CreateMember&gt;**](CreateMember.md)|  | |

### Return type

[**List&lt;Member&gt;**](Member.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Members created successfully |  -  |
| **400** | Invalid request - Possible reasons: - Member with bad sponsors (less than 2, or not confirmed members) - Sponsor seniority less than 90 days - Sponsors from target collectivity less than sponsors from other collectivities - Registration fees or membership dues not paid  |  -  |
| **404** | Collectivity or sponsor not found |  -  |

