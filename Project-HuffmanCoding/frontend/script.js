document.addEventListener("DOMContentLoaded", () => {
  const inputText = document.getElementById("inputText");
  const compressBtn = document.getElementById("compressBtn");
  const errorMessage = document.getElementById("errorMessage");
  const resultsContainer = document.getElementById("results");
  const originalBinaryDiv = document.getElementById("originalBinary");
  const compressedBinaryDiv = document.getElementById("compressedBinary");
  const compressionRatioDiv = document.getElementById("compressionRatio");
  const codeTableDiv = document.getElementById("codeTable");
  const codeTableSection = document.getElementById("codeTableSection");

  function textToBinary(text) {
    return Array.from(text)
      .map((char) => char.charCodeAt(0).toString(2).padStart(8, "0"))
      .join("");
  }

  compressBtn.addEventListener("click", async () => {
    const text = inputText.value;
    if (!text) return;

    errorMessage.classList.add("hidden");
    resultsContainer.classList.add("hidden");
    compressBtn.textContent = "Compressing...";
    compressBtn.disabled = true;

    try {
      const originalBinary = textToBinary(text);

      const response = await fetch(
        "https://huffman-project-yes.onrender.com/compress",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
          body: JSON.stringify({ text: text }),
        },
      );

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      const data = await response.json();

      if (data.error) {
        throw new Error(data.error);
      }

      const compressedBinary = data.compressed || "";
      const originalBits = originalBinary.length;
      const compressedBits = compressedBinary.length;
      const ratio =
        compressedBits > 0
          ? (originalBits / compressedBits).toFixed(2)
          : "0.00";

      originalBinaryDiv.textContent = originalBinary;
      compressedBinaryDiv.textContent = compressedBinary;
      compressionRatioDiv.textContent = `${ratio}x (Original: ${originalBits} bits, Compressed: ${compressedBits} bits)`;

      codeTableDiv.innerHTML = "";
      if (data.codes && Object.keys(data.codes).length > 0) {
        codeTableSection.classList.remove("hidden");
        for (const [char, code] of Object.entries(data.codes)) {
          let displayChar = char;
          if (char === " ") displayChar = "Space";
          else if (char === "\n") displayChar = "↵";
          else if (char === "\t") displayChar = "Tab";
          else if (char === "\r") displayChar = "\\r";

          const item = document.createElement("div");
          item.className = "code-item";
          item.innerHTML = `
                        <div class="code-char">${displayChar}</div>
                        <div class="code-val">${code}</div>
                    `;
          codeTableDiv.appendChild(item);
        }
      } else {
        codeTableSection.classList.add("hidden");
      }

      resultsContainer.classList.remove("hidden");
    } catch (error) {
      console.error("Compression error:", error);
      errorMessage.textContent = "Server error, try again";
      errorMessage.classList.remove("hidden");
    } finally {
      compressBtn.textContent = "Compress";
      compressBtn.disabled = false;
    }
  });
});
