Feature: Google calendar launch and login

Scenario: UI
Given I launch Google Calendar
When Creator logs with username as "jifflenow.creator" and password as "Jiffle123"
And I modify the time format to "13:00"
And I create an event with name as "Event123" startdate as "7/11/2017" starttime as "21:00" enddate as "7/11/2017" endtime as "22:00" with guest as "attendee.jifflenow@gmail.com"
And I logout
Given I launch Google Calendar
When Attendee logs with username as "attendee.jifflenow" and password as "Jiffle123"
And Open the event "Event123" and startDate as "7/11/2017"
Then I validate the event name as "Event123" startdate as "7/11/2017" starttime as "21:00" enddate as "7/11/2017" endtime as "22:00"
And I logout

Scenario: API
Given I request as user "jifflenow.creator@gmail.com" with accesscode as "ya29.GlucBNX7TTMdMzJTZnXN8DgDnOVsiayCSCGjhvu5rkr_cfhWELy7ZviVrdBVLRFm71zAuCnY5QMrZqoxhXOLWU5Qm3EdcC61JY9LXj8ZTc656zMO6nZLGJkXRWfS"
When I try to get list of events
Then the status code is 200
And response includes the following
|kind|calendar#calendarListEntry|


#And Select the date with date as "30" and month as "December" and year as "2015"
