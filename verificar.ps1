$ErrorActionPreference = 'Stop'
Push-Location $PSScriptRoot
try {
    New-Item -ItemType Directory -Force build | Out-Null
    $fuentesTaller = @(Get-ChildItem -LiteralPath src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
    & javac -encoding UTF-8 -Xlint:all -Werror -d build @fuentesTaller
    if ($LASTEXITCODE -ne 0) { throw 'La compilacion fallo.' }
    & java -cp build pruebas.Verificacion
    if ($LASTEXITCODE -ne 0) { throw 'Las pruebas fallaron.' }
} finally {
    Pop-Location
}
