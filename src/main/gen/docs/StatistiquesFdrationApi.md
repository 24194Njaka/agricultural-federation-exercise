# StatistiquesFdrationApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getRapportAnnuelFederation**](StatistiquesFdrationApi.md#getRapportAnnuelFederation) | **GET** /federation/statistiques/rapport-annuel | Rapport annuel global de la fédération |
| [**getRapportMensuelFederation**](StatistiquesFdrationApi.md#getRapportMensuelFederation) | **GET** /federation/statistiques/rapport-mensuel | Rapport mensuel global de la fédération |
| [**getStatsCollectivites**](StatistiquesFdrationApi.md#getStatsCollectivites) | **GET** /federation/statistiques/collectivites | Statistiques comparatives de toutes les collectivités |


<a id="getRapportAnnuelFederation"></a>
# **getRapportAnnuelFederation**
> RapportAnnuelFederation getRapportAnnuelFederation(annee)

Rapport annuel global de la fédération

Rapport annuel présenté lors des assemblées générales. Contient les mêmes indicateurs que le rapport mensuel, agrégés sur l&#39;année. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StatistiquesFdrationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    StatistiquesFdrationApi apiInstance = new StatistiquesFdrationApi(defaultClient);
    Integer annee = 2025; // Integer | 
    try {
      RapportAnnuelFederation result = apiInstance.getRapportAnnuelFederation(annee);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatistiquesFdrationApi#getRapportAnnuelFederation");
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
| **annee** | **Integer**|  | |

### Return type

[**RapportAnnuelFederation**](RapportAnnuelFederation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Rapport annuel de la fédération |  -  |

<a id="getRapportMensuelFederation"></a>
# **getRapportMensuelFederation**
> RapportMensuelFederation getRapportMensuelFederation(annee, mois)

Rapport mensuel global de la fédération

Pour chaque collectivité sur une période donnée : - Taux d&#39;assiduité global - Pourcentage de membres à jour dans leurs cotisations - Nombre de nouveaux adhérents 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StatistiquesFdrationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    StatistiquesFdrationApi apiInstance = new StatistiquesFdrationApi(defaultClient);
    Integer annee = 2026; // Integer | 
    Integer mois = 4; // Integer | 
    try {
      RapportMensuelFederation result = apiInstance.getRapportMensuelFederation(annee, mois);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatistiquesFdrationApi#getRapportMensuelFederation");
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
| **annee** | **Integer**|  | |
| **mois** | **Integer**|  | |

### Return type

[**RapportMensuelFederation**](RapportMensuelFederation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Rapport mensuel de la fédération |  -  |

<a id="getStatsCollectivites"></a>
# **getStatsCollectivites**
> List&lt;StatsCollectivite&gt; getStatsCollectivites(dateDebut, dateFin)

Statistiques comparatives de toutes les collectivités

Vue consolidée permettant de comparer les collectivités.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StatistiquesFdrationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    StatistiquesFdrationApi apiInstance = new StatistiquesFdrationApi(defaultClient);
    LocalDate dateDebut = LocalDate.now(); // LocalDate | 
    LocalDate dateFin = LocalDate.now(); // LocalDate | 
    try {
      List<StatsCollectivite> result = apiInstance.getStatsCollectivites(dateDebut, dateFin);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatistiquesFdrationApi#getStatsCollectivites");
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
| **dateDebut** | **LocalDate**|  | |
| **dateFin** | **LocalDate**|  | |

### Return type

[**List&lt;StatsCollectivite&gt;**](StatsCollectivite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Statistiques comparatives |  -  |

