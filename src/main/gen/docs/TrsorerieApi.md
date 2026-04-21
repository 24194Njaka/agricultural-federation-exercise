# TrsorerieApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createCompteCollectivite**](TrsorerieApi.md#createCompteCollectivite) | **POST** /collectivites/{collectiviteId}/comptes | Ajouter un compte à une collectivité |
| [**createCompteFederation**](TrsorerieApi.md#createCompteFederation) | **POST** /federation/comptes | Ajouter un compte à la fédération |
| [**getCompteCollectivite**](TrsorerieApi.md#getCompteCollectivite) | **GET** /collectivites/{collectiviteId}/comptes/{compteId} | Obtenir les détails d&#39;un compte |
| [**getSoldeCompte**](TrsorerieApi.md#getSoldeCompte) | **GET** /collectivites/{collectiviteId}/comptes/{compteId}/solde | Obtenir le solde d&#39;un compte à une date donnée |
| [**getSoldeCompteFederation**](TrsorerieApi.md#getSoldeCompteFederation) | **GET** /federation/comptes/{compteId}/solde | Obtenir le solde d&#39;un compte de la fédération |
| [**listCompteFederation**](TrsorerieApi.md#listCompteFederation) | **GET** /federation/comptes | Lister les comptes de la fédération |
| [**listComptesCollectivite**](TrsorerieApi.md#listComptesCollectivite) | **GET** /collectivites/{collectiviteId}/comptes | Lister les comptes d&#39;une collectivité |
| [**updateCompteCollectivite**](TrsorerieApi.md#updateCompteCollectivite) | **PUT** /collectivites/{collectiviteId}/comptes/{compteId} | Mettre à jour un compte |


<a id="createCompteCollectivite"></a>
# **createCompteCollectivite**
> Compte createCompteCollectivite(collectiviteId, compteRequest)

Ajouter un compte à une collectivité

Une collectivité ne peut avoir qu&#39;une seule caisse. Elle peut avoir plusieurs comptes bancaires ou mobile money. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TrsorerieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TrsorerieApi apiInstance = new TrsorerieApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    CompteRequest compteRequest = new CompteRequest(); // CompteRequest | 
    try {
      Compte result = apiInstance.createCompteCollectivite(collectiviteId, compteRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TrsorerieApi#createCompteCollectivite");
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
| **collectiviteId** | **UUID**| Identifiant unique de la collectivité | |
| **compteRequest** | [**CompteRequest**](CompteRequest.md)|  | |

### Return type

[**Compte**](Compte.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Compte créé |  -  |
| **409** | Conflit - une caisse existe déjà |  -  |
| **422** |  |  -  |

<a id="createCompteFederation"></a>
# **createCompteFederation**
> Compte createCompteFederation(compteRequest)

Ajouter un compte à la fédération

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TrsorerieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TrsorerieApi apiInstance = new TrsorerieApi(defaultClient);
    CompteRequest compteRequest = new CompteRequest(); // CompteRequest | 
    try {
      Compte result = apiInstance.createCompteFederation(compteRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TrsorerieApi#createCompteFederation");
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
| **compteRequest** | [**CompteRequest**](CompteRequest.md)|  | |

### Return type

[**Compte**](Compte.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Compte créé |  -  |
| **409** | Une caisse existe déjà pour la fédération |  -  |

<a id="getCompteCollectivite"></a>
# **getCompteCollectivite**
> Compte getCompteCollectivite(collectiviteId, compteId)

Obtenir les détails d&#39;un compte

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TrsorerieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TrsorerieApi apiInstance = new TrsorerieApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID compteId = UUID.randomUUID(); // UUID | Identifiant unique du compte
    try {
      Compte result = apiInstance.getCompteCollectivite(collectiviteId, compteId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TrsorerieApi#getCompteCollectivite");
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
| **collectiviteId** | **UUID**| Identifiant unique de la collectivité | |
| **compteId** | **UUID**| Identifiant unique du compte | |

### Return type

[**Compte**](Compte.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Détails du compte |  -  |
| **404** |  |  -  |

<a id="getSoldeCompte"></a>
# **getSoldeCompte**
> SoldeResponse getSoldeCompte(collectiviteId, compteId, date)

Obtenir le solde d&#39;un compte à une date donnée

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TrsorerieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TrsorerieApi apiInstance = new TrsorerieApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID compteId = UUID.randomUUID(); // UUID | Identifiant unique du compte
    LocalDate date = LocalDate.now(); // LocalDate | Date à laquelle consulter le solde (défaut = aujourd'hui)
    try {
      SoldeResponse result = apiInstance.getSoldeCompte(collectiviteId, compteId, date);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TrsorerieApi#getSoldeCompte");
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
| **collectiviteId** | **UUID**| Identifiant unique de la collectivité | |
| **compteId** | **UUID**| Identifiant unique du compte | |
| **date** | **LocalDate**| Date à laquelle consulter le solde (défaut &#x3D; aujourd&#39;hui) | [optional] |

### Return type

[**SoldeResponse**](SoldeResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Solde du compte |  -  |
| **404** |  |  -  |

<a id="getSoldeCompteFederation"></a>
# **getSoldeCompteFederation**
> SoldeResponse getSoldeCompteFederation(compteId, date)

Obtenir le solde d&#39;un compte de la fédération

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TrsorerieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TrsorerieApi apiInstance = new TrsorerieApi(defaultClient);
    UUID compteId = UUID.randomUUID(); // UUID | Identifiant unique du compte
    LocalDate date = LocalDate.now(); // LocalDate | 
    try {
      SoldeResponse result = apiInstance.getSoldeCompteFederation(compteId, date);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TrsorerieApi#getSoldeCompteFederation");
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
| **compteId** | **UUID**| Identifiant unique du compte | |
| **date** | **LocalDate**|  | [optional] |

### Return type

[**SoldeResponse**](SoldeResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Solde du compte |  -  |
| **404** |  |  -  |

<a id="listCompteFederation"></a>
# **listCompteFederation**
> List&lt;Compte&gt; listCompteFederation(type)

Lister les comptes de la fédération

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TrsorerieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TrsorerieApi apiInstance = new TrsorerieApi(defaultClient);
    TypeCompteEnum type = TypeCompteEnum.fromValue("CAISSE"); // TypeCompteEnum | 
    try {
      List<Compte> result = apiInstance.listCompteFederation(type);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TrsorerieApi#listCompteFederation");
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
| **type** | [**TypeCompteEnum**](.md)|  | [optional] [enum: CAISSE, BANCAIRE, MOBILE_MONEY] |

### Return type

[**List&lt;Compte&gt;**](Compte.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des comptes de la fédération |  -  |

<a id="listComptesCollectivite"></a>
# **listComptesCollectivite**
> List&lt;Compte&gt; listComptesCollectivite(collectiviteId, type)

Lister les comptes d&#39;une collectivité

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TrsorerieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TrsorerieApi apiInstance = new TrsorerieApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    TypeCompteEnum type = TypeCompteEnum.fromValue("CAISSE"); // TypeCompteEnum | 
    try {
      List<Compte> result = apiInstance.listComptesCollectivite(collectiviteId, type);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TrsorerieApi#listComptesCollectivite");
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
| **collectiviteId** | **UUID**| Identifiant unique de la collectivité | |
| **type** | [**TypeCompteEnum**](.md)|  | [optional] [enum: CAISSE, BANCAIRE, MOBILE_MONEY] |

### Return type

[**List&lt;Compte&gt;**](Compte.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des comptes |  -  |
| **404** |  |  -  |

<a id="updateCompteCollectivite"></a>
# **updateCompteCollectivite**
> Compte updateCompteCollectivite(collectiviteId, compteId, compteRequest)

Mettre à jour un compte

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TrsorerieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TrsorerieApi apiInstance = new TrsorerieApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID compteId = UUID.randomUUID(); // UUID | Identifiant unique du compte
    CompteRequest compteRequest = new CompteRequest(); // CompteRequest | 
    try {
      Compte result = apiInstance.updateCompteCollectivite(collectiviteId, compteId, compteRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TrsorerieApi#updateCompteCollectivite");
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
| **collectiviteId** | **UUID**| Identifiant unique de la collectivité | |
| **compteId** | **UUID**| Identifiant unique du compte | |
| **compteRequest** | [**CompteRequest**](CompteRequest.md)|  | |

### Return type

[**Compte**](Compte.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Compte mis à jour |  -  |
| **404** |  |  -  |

