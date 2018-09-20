<!DOCTYPE html>
<html lang="en">
<head>
	<title>Table V01</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="Table_Responsive_v1/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Responsive_v1/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Responsive_v1/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Responsive_v1/vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Responsive_v1/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Table_Responsive_v1/css/util.css">
	<link rel="stylesheet" type="text/css" href="Table_Responsive_v1/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
        <button onclick="llenarTabla();" class="btn btn-success" > Actualizar Tabla </button>
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100">
					<table id="tablaEncuestas">
						<thead>
							<tr class="table100-head">
								<th class="column1">Código</th>
								<th class="column2">Nombre</th>
								<th class="column3">Sector</th>
								<th class="column4">Nivel Escolar</th>
								<th class="column5">Latitud</th>
								<th class="column6">Longitud</th>
								<th class="column7">Modificar</th>
								<th class="column8">Eliminar</th>
							</tr>
						</thead>
						<tbody id="tb">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
    <!-- Include Dexie -->
    <script src="https://unpkg.com/dexie@latest/dist/dexie.js"></script>

	<script>
		function insertarFila(encuesta){
            var table = document.getElementsByTagName("tbody")[0];
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);

            var codigo = row.insertCell(0);
            var nombre = row.insertCell(1);
            var sector = row.insertCell(2);
            var nivelEscolar = row.insertCell(3);
            var lat = row.insertCell(4);
            var lon = row.insertCell(5);
            var modificarBtn = row.insertCell(6);
            var eliminarBtn = row.insertCell(7);


            codigo.className = "column1";
            nombre.className = "column2";
            sector.className = "column3";
            nivelEscolar.className = "column4";
            lat.className = "column5";
            lon.className = "column6";
            modificarBtn.className = "column7";
            eliminarBtn.className = "column8";

			codigo.innerHTML = encuesta.idEncuesta;
            nombre.innerHTML = encuesta.nombre;
            sector.innerHTML = encuesta.sector;
            nivelEscolar.innerHTML = encuesta.nivel;
            lat.innerHTML = encuesta.latitud;
            lon.innerHTML = encuesta.longitud;
            modificarBtn.innerHTML = '<button class="btn btn-success" > Modificar </button>';
			eliminarBtn.innerHTML = '<button class="btn btn-success" > Eliminar </button>';
        }
        function llenarTabla() {
            var db = new Dexie("EncuestasDB");
            db.version(1).stores({
                encuestas: '++idEncuesta,nombre,sector,nivel,longitud,latitud'
            });

            db.encuestas
					.toArray()
                    .then(function (encuestas) {
                        var table = document.getElementsByTagName("tbody")[0];
                        if(encuestas.length > 0 && table.rows.length != encuestas.length){
                            while(table.rows.length > 0) {
                                table.deleteRow(0);
                            }
                            for(var i=0; i < encuestas.length; i++ ){
                                console.log(encuestas.toString());
                                insertarFila(encuestas[i]);
                            }
						}
                    });
        }

        function eliminarEncuesta(idEncuesta){
            var db = new Dexie("EncuestasDB");
            db.version(1).stores({
                encuestas: '++idEncuesta,nombre,sector,nivel,longitud,latitud'
            });
            db.encuestas.delete(idEncuesta);
            llenarTabla();
        }
	</script>
</body>
</html>