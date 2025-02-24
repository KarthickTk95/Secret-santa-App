import React, { useState } from "react";
import { uploadEmployees } from "../api";

const UploadEmployees = ({ onUploadSuccess }) => {
  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleUpload = async () => {
    if (!file) {
      setMessage("Please select a file.");
      return;
    }

    setLoading(true);
    setMessage("");

    try {
      await uploadEmployees(file);
      setMessage("Upload successful!");
      onUploadSuccess(); // Refresh employee list
    } catch (error) {
      console.error("Error uploading file:", error);
      setMessage("Upload failed.");
    }

    setLoading(false);
  };

  return (
    <div className="p-6 bg-white shadow-lg rounded-lg">
      <h2 className="text-xl font-bold mb-4">Upload Employees (CSV)</h2>
      <input
        type="file"
        accept=".csv"
        onChange={handleFileChange}
        className="mb-2"
      />
      <button
        onClick={handleUpload}
        disabled={loading}
        className="bg-blue-500 px-4 py-2 rounded disabled:bg-gray-400"
      >
        {loading ? "Uploading..." : "Upload"}
      </button>
      {message && <p className="mt-2">{message}</p>}
    </div>
  );
};

export default UploadEmployees;
