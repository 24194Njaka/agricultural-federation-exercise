

# Membre


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Long** |  |  [optional] |
|**nom** | **String** |  |  [optional] |
|**prenoms** | **String** |  |  [optional] |
|**dateNaissance** | **LocalDate** |  |  [optional] |
|**genre** | [**GenreEnum**](#GenreEnum) |  |  [optional] |
|**adresse** | **String** |  |  [optional] |
|**metier** | **String** |  |  [optional] |
|**telephone** | **String** |  |  [optional] |
|**email** | **String** |  |  [optional] |
|**dateAdhesion** | **LocalDate** |  |  [optional] |
|**poste** | [**PosteEnum**](#PosteEnum) |  |  [optional] |



## Enum: GenreEnum

| Name | Value |
|---- | -----|
| MASCULIN | &quot;MASCULIN&quot; |
| FEMININ | &quot;FEMININ&quot; |



## Enum: PosteEnum

| Name | Value |
|---- | -----|
| PRESIDENT | &quot;PRESIDENT&quot; |
| PRESIDENT_ADJOINT | &quot;PRESIDENT_ADJOINT&quot; |
| TRESORIER | &quot;TRESORIER&quot; |
| SECRETAIRE | &quot;SECRETAIRE&quot; |
| MEMBRE_CONFIRME | &quot;MEMBRE_CONFIRME&quot; |
| MEMBRE_JUNIOR | &quot;MEMBRE_JUNIOR&quot; |



