# Documentation Utilisateur PPe Gestion des Utilsateur et des Ligues  

## Sommaire  
<ul>
    <li><a href="#sommaire">Sommaire</a></li>
    <li><a href="#installation">Installation</a></li>
    <li><a href="#manuel">Manuel d'utilisation</a></li>
</ul>

## Installation

Pour installer l'application de gestion des ligues et des utilisateur.  
il va vous falloir cliquer [ici](https://github.com/InMemoriam7th/PPE-Groupe-1) pour récuperer l'application.  

Une fois l'application mise en place il va falloir configurer la base de données.  
Le script de création est fourni avec l'application, il est dans le fichier **Script BDD.txt**.  

Ensuite il va falloir configurer l'application avec les parametres de la base de données créé.  
Copiez le fichier **CredentialsExample** et renommez le **Credentials**, ensuite configurez les acces de la base dedans.  

Pour finir, il va falloir installer les dépendances Maven:<br> Depuis Eclipse ->selectionner l'aplication -> run as -> Maven build -> Maven install.  
  
Une fois que tout est fait, votre application est prête a l'emploi.

## Manuel d'utilisation

D'abord connectez vous à l'application, les identifiants sont:  
**root** pour l'identifiant  
**toor** pour le mot de passe  

Vous serez connecter a l'application avec le compte **Root**, il permet d'outrepasser toute les autorisations et de creer, de modifier et de supprimer des employés et des ligues.  

### **Creer une ligue**

Depuis la page principale, selectionnez "Gérer les ligues", puis "ajouter une ligue", renseignez le nom de la ligue que vous voulez créer et c'est fait.  

### **Modifier ou supprimer une ligue**

Depuis la page "Gestion des ligues", sélectionnez la ligue a modifier ou a supprimer, et sur la page suivante vous pouvez soit modifier le nom de la ligue ou la supprimer.  

### **Ajouter un employé**  

Depuis la page "Gestion des ligues", sélectionnez la ligue où l'employé doit être ajouté, et sur la page suivante, sélectionnez "éditer employés", puis "Ajouter un employé", renseignez les informations de l'employé pour terminer.  

### **Modifier ou supprimer un employé**

Depuis la page "Gestion des ligues", sélectionnez la ligue où se trouve l'employé qui doit être modifier ou supprimer, et sur la page suivante, sélectionnez "éditer employés", puis l'employé en question, sélectionnez "Modifier", sur la page suivante vous pouvez modifier les information de l'employé ou le supprimer.  