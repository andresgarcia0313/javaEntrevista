# Define la ruta relativa para los archivos
$relativePath = pwd
$relativePath = Get-Location

# Obtiene la lista de archivos en la ruta relativa
$files = Get-ChildItem -Path $relativePath -Recurse -File

# Ruta del archivo de salida
$outputFile = "codigo.txt"

# Función para obtener el contenido de un archivo y escribirlo en el archivo de salida
function AppendToFile($filePath) {
    $fileTitle = (Get-Item $filePath).FullName
    $fileContent = Get-Content $filePath -Raw
    Add-Content -Path $outputFile -Value ("Titulo De Archivo: $fileTitle`r`n")
    Add-Content -Path $outputFile -Value ("Contenido del archivo:`r`n$fileContent`r`n`r`n")
}

# Recorre la lista de archivos y copia su contenido al archivo de salida
foreach ($file in $files) {
    AppendToFile $file.FullName
}

# Muestra mensaje de éxito
Write-Host "Se ha completado la tarea. Los archivos y su contenido se han copiado en '$outputFile'."
Write-Host "Gracias"

