

# AdmissionMembreRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**nom** | **String** |  |  |
|**prenom** | **String** |  |  |
|**dateNaissance** | **LocalDate** |  |  |
|**genre** | **GenreEnum** |  |  |
|**adresse** | **String** |  |  |
|**metier** | **String** |  |  |
|**telephone** | **String** |  |  |
|**email** | **String** |  |  |
|**parrains** | [**List&lt;ParrainInfo&gt;**](ParrainInfo.md) | Au moins 2 membres confirmés parrains. Le nombre de parrains issus de la collectivité cible doit être ≥ nombre de parrains d&#39;autres collectivités.  |  |
|**paiement** | [**PaiementAdmissionRequest**](PaiementAdmissionRequest.md) |  |  |



