# Write your MySQL query statement below
SELECT e.name AS Employee 
FROM Employee AS e
JOIN Employee AS e1
ON e.managerId = e1.id
WHERE e.salary > e1.salary