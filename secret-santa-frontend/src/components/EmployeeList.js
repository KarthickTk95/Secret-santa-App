import React, { useEffect, useState } from "react";
import { fetchEmployees } from "../api";

const EmployeeList = () => {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    const loadEmployees = async () => {
      try {
        const data = await fetchEmployees();
        setEmployees(data);
      } catch (error) {
        console.error("Error fetching employees:", error);
      }
    };

    loadEmployees();
  }, []);

  return (
    <div className="p-6 bg-white shadow-lg rounded-lg">
      <h2 className="text-xl font-bold mb-4">Employee List</h2>
      {employees.length === 0 ? (
        <p>No employees found.</p>
      ) : (
        <ul className="space-y-2">
          {employees.map((emp, index) => (
            <li key={index} className="border p-2 rounded-md shadow-sm">
              {emp.name} - {emp.email}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default EmployeeList;
