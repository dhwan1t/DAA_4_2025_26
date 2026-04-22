document.addEventListener("DOMContentLoaded", () => {
  const inputText = document.getElementById("inputText");
  const compressBtn = document.getElementById("compressBtn");
  const btnText = compressBtn.querySelector(".btn-text");
  const loader = compressBtn.querySelector(".loader");

  const outputSection = document.getElementById("outputSection");
  const compressionRatio = document.getElementById("compressionRatio");
  const originalBinary = document.getElementById("originalBinary");
  const compressedBinary = document.getElementById("compressedBinary");
  const codeTable = document.getElementById("codeTable");

  // Disable/Enable button based on input
  inputText.addEventListener("input", () => {
    compressBtn.disabled = inputText.value.trim() === "";
  });

  function textToBinary(text) {
    return text
      .split("")
      .map((char) => char.charCodeAt(0).toString(2).padStart(8, "0"))
      .join(" ");
  }

  compressBtn.addEventListener("click", async () => {
    const text = inputText.value;
    if (!text) return;

    // Generate and display original binary before making API call
    originalBinary.textContent = textToBinary(text);

    // Clear previous results while loading
    compressedBinary.textContent = "";
    compressionRatio.textContent = "--";
    codeTable.innerHTML = "";

    // Show output section early so original binary is visible during loading
    outputSection.classList.remove("hidden");

    // UI Loading state
    compressBtn.disabled = true;
    btnText.classList.add("hidden");
    loader.classList.remove("hidden");

    try {
      const response = await fetch("http://localhost:8080/compress", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ text }),
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      displayResults(data);
    } catch (error) {
      console.error("Compression failed:", error);
      alert(
        "Failed to compress text. Make sure the backend server is running on http://localhost:8080.",
      );
    } finally {
      // Restore UI state
      compressBtn.disabled = inputText.value.trim() === "";
      btnText.classList.remove("hidden");
      loader.classList.add("hidden");
    }
  });

  function displayResults(data) {
    // Handle various common JSON keys for flexibility
    const compressed =
      data.compressed ||
      data.compressedString ||
      data.encoded ||
      data.result ||
      "";
    const ratio = data.ratio || data.compressionRatio || "";
    const codes = data.codes || data.huffmanCodes || data.table || {};

    // 1. Compressed Binary String
    compressedBinary.textContent = compressed;

    // 2. Compression Ratio
    if (typeof ratio === "number") {
      compressionRatio.textContent = ratio.toFixed(2);
    } else {
      compressionRatio.textContent = ratio || "N/A";
    }

    // 3. Code Table
    codeTable.innerHTML = "";
    if (Object.keys(codes).length > 0) {
      for (const [char, val] of Object.entries(codes)) {
        const itemDiv = document.createElement("div");
        itemDiv.className = "code-item";

        const charDiv = document.createElement("div");
        charDiv.className = "code-char";

        // Handle whitespace characters for display
        if (char === " ") charDiv.textContent = "SPACE";
        else if (char === "\n") charDiv.textContent = "\\n";
        else if (char === "\t") charDiv.textContent = "\\t";
        else charDiv.textContent = char;

        const valDiv = document.createElement("div");
        valDiv.className = "code-val";
        valDiv.textContent = val;

        itemDiv.appendChild(charDiv);
        itemDiv.appendChild(valDiv);
        codeTable.appendChild(itemDiv);
      }
    } else {
      codeTable.textContent = "No codes generated.";
    }
  }
});
