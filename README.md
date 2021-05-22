# Voting system for deciding where to have lunch.

This system is a REST API without frontend, which allows the user to choose the best restaurant based on a vote.

Technology stack: Java 15/Hibernate/Spring-Boot/H2 Database.

## Features

• Admin can input a restaurant and it's lunch menu of the day
`POST /rest/restaurant/admin/add`

• Menu changes each day (admins do the updates)
`POST /rest/restaurant/admin/add/setmenu`

• Users can vote on which restaurant they want to have lunch at (Only one vote counted per user)
`POST /rest/restaurant/{id}`

## Curl samples

#### create Restaurant with lunch menu of the day

`curl -s -X POST -d '{"name": "first","dishes":[{"name": "breakfast","price":100},{"name":"dinner","price": 200}]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/restaurant/admin/add --user admin3@ya.ru:p`

#### add lunch menu of the day to Restaurant

`curl -s -X POST -d '[{"name": "borscht","price": 100,"restaurant":{"id":1005}},{"name": "pancakes","price": 1000,"restaurant":{"id":1005}}]' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/restaurant/admin/setmenu --user admin3@ya.ru:p`

#### restaurant vote

`curl -s -X POST -d -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/restaurant/1005 --user user0@ya.ru:p`


