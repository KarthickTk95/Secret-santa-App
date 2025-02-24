import React, { useState } from "react";
import axios from "axios";

const SecretSantaApp = () => {
  const [fileUrl, setFileUrl] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const generateAssignments = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await axios.post("http://localhost:8080/api/secretsanta/generate", {}, {
        responseType: "blob",
      });

      const url = window.URL.createObjectURL(new Blob([response.data]));
      setFileUrl(url);
    } catch (err) {
      setError("Failed to generate Secret Santa assignments.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">Secret Santa Generator</h1>
      <button
        onClick={generateAssignments}
        className="bg-blue-500 px-4 py-2 rounded"
        disabled={loading}
      >
        {loading ? "Generating..." : "Generate Assignments"}
      </button>
      {fileUrl && (
        <div className="mt-4">
          <a
            href={fileUrl}
            download="secret_santa_assignments.csv"
            className="text-blue-600 underline"
          >
            Download CSV File
          </a>
        </div>
      )}
      {error && <p className="text-red-500 mt-2">{error}</p>}
    </div>
  );
};

export default SecretSantaApp;
