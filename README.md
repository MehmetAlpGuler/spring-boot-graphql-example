# GraphQL-Spring-boot Application


## 1. In Postgres Database used.
    
    docker-compose up -d

## 2. Running as a Packaged Application (Following ways)
     mvn spring-boot:run (using maven)
     
     OR
     
     java -jar target/spring-boot-graphql-0.0.1-SNAPSHOT.jar

   

## 3. Test
    http://localhost:8080/graphiql
    
    OR
    
    Postman

## 4. Querys

####CREATE 

#####Create Profile

    request 
    
    mutation {
          createProfile(input: {username: "Alp", bio: "Developer"}){
            id
          }
        }
    
    response
    
    {
      "data": {
        "createProfile": {
          "id": 1
        }
      }
    }
    
#####Create Article

    request    
    
    mutation {
          createArticle(input: {title:"India", text:"testtt", authorId:1}){
            id
          }
        }
    
    
    response
    
    {
      "data": {
        "createArticle": {
          "id": 1
        }
      }
    }


####SELECT

#####Example 1
    
    request
    
    query AllArticles {
      articles {
        id
        title
        author {
          id
          username
        }
      }
    }
    
    response
    
    {
      "data": {
        "articles": [
          {
            "id": 2,
            "title": "Foo",
            "author": {
              "id": 2,
              "username": "admin"
            }
          },
          {
            "id": 1,
            "title": "Hello world",
            "author": {
              "id": 1,
              "username": "g00glen00b"
            }
          },
          {
            "id": 3,
            "title": "India",
            "author": {
              "id": 1,
              "username": "g00glen00b"
            }
          }
        ]
      }
    }
    
    
#####Example 2

    request
    
    query Article($articleId: Int!) {
      article(id: $articleId) {
        id
        title
        author {
          id
          username
          bio
        }
        comments {
          id
          text
          author {
            id
            username
          }
        }
      }
    }
    
    @QUERY VARIABLES
    {"articleId": 1}
    
    response
    
    {
      "data": {
        "article": {
          "id": 1,
          "title": "Hello world",
          "author": {
            "id": 1,
            "username": "g00glen00b",
            "bio": "The author of this blog"
          },
          "comments": [
            {
              "id": 1,
              "text": "Do you like this article?",
              "author": {
                "id": 1,
                "username": "g00glen00b"
              }
            },
            {
              "id": 2,
              "text": "This is a great article",
              "author": {
                "id": 2,
                "username": "admin"
              }
            }
          ]
        }
      }
    }
    
####UPDATE


#####Profile Update

    request
    
    mutation 
        {
          updateProfile(input: {id: 3, bio:"Mehmet"}){
            bio 
          }
        }
    
    response 
    
    {
      "data": {
        "updateProfile": {
          "bio": "Mehmet"
        }
      }
    }
    
#####Article Update
    
    request
    
    mutation 
        {
          updateArticle(input: {id: 3, title:"Mehmet", text:"Testttt"}){
            title
          }
        }
        
     response
        
     {
       "data": {
         "updateArticle": {
           "title": "Mehmet"
         }
       }
     }   
     
####DELETE

#####Profile Delete

    request
    
    mutation {
          deleteProfile(id:3)
        }
        
    response
    
    {
      "data": {
        "deleteProfile": 1
      }
    }
    
#####Article Delete

    request
    
    mutation {
              deleteArticle(id:3)
            }
    
    
    response
    
    {
      "data": {
        "deleteArticle": 1
      }
    }
    
