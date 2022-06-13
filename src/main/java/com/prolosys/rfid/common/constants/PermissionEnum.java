package com.prolosys.rfid.common.constants;

public enum PermissionEnum {
	
	/*====================================================
	 * Permisos genéricos para todas las aplicaciones
	 * ====================================================*/
	
	/* USUARIOS */
	SCL_USERS_CREATE(					"USERS	%%	POST	%%	Crear usuarios"),
	SCL_USERS_UPDATE_BYID(				"USERS	%%	PUT		%%	Actualizar usuarios"),
	SCL_USERS_ACTIVE_LIST(				"USERS	%%	GET		%%	Ver lista de usuarios habilitados"),
	SCL_USERS_INACTIVE_LIST(			"USERS	%%	GET		%%	Ver lista de usuarios deshabilitados"),
	SCL_USERS_VIEW_BYID(				"USERS	%%	GET		%%	Ver detalle de usuarios"),
	SCL_USERS_ENABLE_BYID(				"USERS	%%	PUT		%%	Habilitar o deshabilitar usuarios"),
	SCL_USERS_UPDATE_MY_PASSWORD(		"USERS	%%	PUT		%%	Actualizar contraseña del usuario con sesión activa"),
	SCL_USERS_EXISTS__BY(				"USERS 	%% 	GET 	%%  Validar si existe un centro por campo" ),
	
	/* ROLES */
	SCL_ROLES_CREATE(				"ROLES	%%	POST	%%	Crear roles"),
	SCL_ROLES_UPDATE_BYID(			"ROLES	%%	PUT		%%	Actualizar roles"),
	SCL_ROLES_ACTIVE_LIST(			"ROLES	%%	GET		%%	Ver lista de roles habilitados"),
	SCL_ROLES_INACTIVE_LIST(		"ROLES	%%	GET		%%	Ver lista de roles deshablitados"),
	SCL_ROLES_VIEW_BYID(			"ROLES	%%	GET		%%	Ver detalle de rol"),
	SCL_ROLES_ENABLE_BYID(			"ROLES	%%	PUT		%%	Habilitar o deshabilitar roles"),
	SCL_ROLES_PERMISSIONS_LIST(		"ROLES	%%	GET		%%	Ver lista de permisos"),
	SCL_ROLES_EXISTS__BY(			"ROLES 	%% 	GET 	%%  Validar si existe un centro por campo"),
	
	/*Zonas*/
	SCL_ZONES_CREATE (			"ZONES 	%% 	POST 	%% 	Crear una zona"),
	SCL_ZONES_UPDATE_BYID(		"ZONES	%%	PUT		%%	Actualizar zona"),
	SCL_ZONES_VIEW_BYID(		"ZONES	%%	GET		%%	Ver el detalle de una zona"),
	SCL_ZONES_ENABLE_BYID(		"ZONES	%%	PUT		%%	Habilitar o deshabilitar zona "),
	SCL_ZONES_ACTIVE_LIST(		"ZONES	%%	GET		%%	Ver lista de zonas habilitadas"),
	SCL_ZONES_INACTIVE_LIST(	"ZONES	%%	GET		%%	Ver lista de zonas deshabilitadas"),
	SCL_ZONES_EXISTS__BY(		"ZONES	%%	GET		%%	Validar si existe una zona por campo"),	
	
	/* CENTROS */
	SCL_CENTERS_CREATE(				"CENTERS	%%	POST	%%	Crear centros"),
	SCL_CENTERS_UPDATE_BYID(		"CENTERS	%%	PUT		%%	Actualizar centros"),
	SCL_CENTERS_ACTIVE_LIST(		"CENTERS	%%	GET		%%	Ver lista de centros habilitados"),
	SCL_CENTERS_INACTIVE_LIST(		"CENTERS	%%	GET		%%	Ver lista de centros deshabilitados"),
	SCL_CENTERS_VIEW_BYID(			"CENTERS	%%	GET		%%	Ver detalle de centro"),
	SCL_CENTERS_ENABLE_BYID(		"CENTERS	%%	PUT		%%	Habilitar o deshabilitar centros"),
	SCL_CENTERS_EXISTS__BY(			"CENTERS 	%% 	GET 	%%  Validar si existe un centro por campo" ),
	
	/* UNIDADES DE ALMACENAMIENTO */
	SCL_STORATEUNIT_CREATE(				"STORATEUNIT	%%	POST	%%	Crear unidad de almacenamiento"),
	SCL_STORATEUNIT_UPDATE_BYID(		"STORATEUNIT	%%	PUT		%%	Actualizar unidad de almacenamiento"),
	SCL_STORATEUNIT_ACTIVE_LIST(		"STORATEUNIT	%%	GET		%%	Ver lista de unidades de almacenamiento habilitadas"),
	SCL_STORATEUNIT_INACTIVE_LIST(		"STORATEUNIT	%%	GET		%%	Ver lista de unidades de almacenamiento deshabilitadas"),
	SCL_STORATEUNIT_VIEW_BYID(			"STORATEUNIT	%%	GET		%%	Ver detalle de unidad de almacenamiento"),
	SCL_STORATEUNIT_ENABLE_BYID(		"STORATEUNIT	%%	PUT		%%	Habilitar o deshabilitar unidad de almacenamiento"),
	SCL_STORATEUNIT_EXISTS__BY(			"STORATEUNIT 	%% 	GET 	%%  Validar si existe una unidad de almacenamiento por campo" ),
	SCL_STORATEUNIT_VIEW_LIST_BY__CONTENT_BYCONTENT_BY__CENTER_BYID(			"STORATEUNIT 	%% 	GET 	%%  Consultar unidades de almacenamiento por granja y tipo de contenido" ),
	SCL_STORATEUNIT_VIEW_ENTRIES_BYID(	"STORATEUNIT	%%	GET		%%	Ver lista de entradas de unidad de almacenamiento"),
	SCL_STORATEUNIT_VIEW_REPORTS_BYID(	"STORATEUNIT	%%	GET		%%	Ver lista de reportes de unidad de almacenamiento"),

	/* Casetas */
	SCL_PRODUCTIVEUNIT_CREATE(				"PRODUCTIVEUNIT		%%	POST	%%	Crear caseta"),
	SCL_PRODUCTIVEUNIT_UPDATE_BYID(			"PRODUCTIVEUNIT		%%	PUT		%%	Actualizar caseta"),
	SCL_PRODUCTIVEUNIT_ACTIVE_LIST(			"PRODUCTIVEUNIT		%%	GET		%%	Ver lista de casetas habilitadas"),
	SCL_PRODUCTIVEUNIT_INACTIVE_LIST(		"PRODUCTIVEUNIT		%%	GET		%%	Ver lista de casetas deshabilitadas"),
	SCL_PRODUCTIVEUNIT_VIEW_BYID(			"PRODUCTIVEUNIT		%%	GET		%%	Ver detalle de caseta"),
	SCL_PRODUCTIVEUNIT_ENABLE_BYID(			"PRODUCTIVEUNIT		%%	PUT		%%	Habilitar o deshabilitar caseta"),
	SCL_PRODUCTIVEUNIT_EXISTS__BY(			"PRODUCTIVEUNIT 	%% 	GET 	%%  Validar si existe una caseta por campo" ),
	
	
	/*Familias de materiales*/
	SCL_FAMILIES_CREATE (			"FAMILIES 	%% 	POST 	%% 	Crear una familia de materiales"),
	SCL_FAMILIES_UPDATE_BYID(		"FAMILIES	%%	PUT		%%	Actualizar familia de materiales"),
	SCL_FAMILIES_VIEW_BYID(			"FAMILIES	%%	GET		%%	Ver el detalle de una familia de materiales"),
	SCL_FAMILIES_ENABLE_BYID(		"FAMILIES	%%	PUT		%%	Habilitar o deshabilitar familia de materiales"),
	SCL_FAMILIES_ACTIVE_LIST(		"FAMILIES	%%	GET		%%	Ver lista de familias de materiales habilitadas"),
	SCL_FAMILIES_INACTIVE_LIST(		"FAMILIES	%%	GET		%%	Ver lista de familias de materiales deshabilitadas"),
	SCL_FAMILIES_EXISTS__BY(		"FAMILIES	%%	GET		%%	Validar si existe un centro por campo"),		
	
	/* MATERIALES */
	SCL_MATERIALS_CREATE(			"MATERIALS	%%	POST	%%	Crear materiales"),
	SCL_MATERIALS_UPDATE_BYID(		"MATERIALS	%%	PUT		%%	Actualizar materiales"),
	SCL_MATERIALS_ACTIVE_LIST(		"MATERIALS	%%	GET		%%	Ver lista de materiales habilitados"),
	SCL_MATERIALS_INACTIVE_LIST(	"MATERIALS	%%	GET		%%	Ver lista de materiales deshabilitados"),
	SCL_MATERIALS_VIEW_BYID(		"MATERIALS	%%	GET		%%	Ver detalle de material"),
	SCL_MATERIALS_ENABLE_BYID(		"MATERIALS	%%	PUT		%%	Habilitar o deshabilitar materiales"),
	SCL_MATERIALS_BY__FAMILY_BYID(	"MATERIALS	%%	GET		%%	Lista de materiales por familia"),
	SCL_MATERIALS_BY__TERM(			"MATERIALS	%%	GET		%%	Lista de materiales por palabra clave"),
	SCL_MATERIALS_EXISTS__BY(		"MATERIALS	%%	GET		%%	Validar si existe un material por un campo"),
	SCL_MATERIALS_BY__VENDOR_BYID(	"MATERIALS 	%% 	GET 	%% 	Ver materiales por proveedor"),
	
	
	/* ACTIVOS FIJOS */
	SCL_FIXEDASSET_CREATE(				"FIXEDASSET	 %%	POST	%%	Crear activo fijo"),
	SCL_FIXEDASSET_UPDATE_BYID(			"FIXEDASSET	 %%	PUT		%%	Actualizar activo fijo"),
	SCL_FIXEDASSET_ACTIVE_LIST(			"FIXEDASSET	 %%	GET		%%	Ver lista de activos fijos habilitados"),
	SCL_FIXEDASSET_INACTIVE_LIST(		"FIXEDASSET	 %%	GET		%%	Ver lista de activos fijos deshabilitados"),
	SCL_FIXEDASSET_VIEW_BYID(			"FIXEDASSET	 %%	GET		%%	Ver detalle de activo fijo"),
	SCL_FIXEDASSET_ENABLE_BYID(			"FIXEDASSET  %%	PUT		%%	Habilitar o deshabilitar activo fijo"),
	SCL_FIXEDASSET_BY__FAMILY_BYID(		"FIXEDASSET	 %%	GET		%%	Lista de activos fijos por familia"),
	SCL_FIXEDASSET_BY__TERM(			"FIXEDASSET	 %%	GET		%%	Lista de activos fijos por palabra clave"),
	SCL_FIXEDASSET_EXISTS__BY(			"FIXEDASSET	 %%	GET		%%	Validar si existe un activo fijo por un campo"),
	SCL_FIXEDASSET_BY__VENDOR_BYID(		"FIXEDASSET  %% GET 	%% 	Ver activos fijos por proveedor"),
	
	/* PROVEEDORES */
	SCL_VENDORS_CREATE(				"VENDORS	%%	POST	%%	Crear proveedores"),
	SCL_VENDORS_UPDATE_BYID(		"VENDORS	%%	PUT		%%	Actualizar proveedores"),
	SCL_VENDORS_ACTIVE_LIST(		"VENDORS	%%	GET		%%	Ver lista de proveedores habilitados"),
	SCL_VENDORS_INACTIVE_LIST(		"VENDORS	%%	GET		%%	Ver lista de proveedores deshabilitados"),
	SCL_VENDORS_VIEW_BYID(			"VENDORS	%%	GET		%%	Ver detalle de un proveedores"),
	SCL_VENDORS_ENABLE_BYID(		"VENDORS	%%	PUT		%%	Habilitar o deshabilitar proveedores"),
	SCL_VENDORS_EXISTS__BY(			"VENDORS 	%% 	GET 	%%  Validar si existe un proveedor por campo" ),
	
	
	/* CLINETES */
	SCL_CLIENTS_CREATE(				"CLIENTS	%%	POST	%%	Crear clientes"),
	SCL_CLIENTS_UPDATE_BYID(		"CLIENTS	%%	PUT		%%	Actualizar clientes"),
	SCL_CLIENTS_ACTIVE_LIST(		"CLIENTS	%%	GET		%%	Ver lista de clientes habilitados"),
	SCL_CLIENTS_INACTIVE_LIST(		"CLIENTS	%%	GET		%%	Ver lista de clientes deshabilitados"),
	SCL_CLIENTS_VIEW_BYID(			"CLIENTS	%%	GET		%%	Ver detalle de un cliente"),
	SCL_CLIENTS_ENABLE_BYID(		"CLIENTS	%%	PUT		%%	Habilitar o deshabilitar clientes"),
	SCL_CLIENTS_EXISTS__BY(			"CLIENTS 	%% 	GET 	%%  Validar si existe un cliente por campo" ),
	
	/* ALMACENES */
	SCL_WAREHOUSES_CREATE(			"WAREHOUSES	%%	POST	%%	Crear almacenes"),
	SCL_WAREHOUSES_UPDATE_BYID(		"WAREHOUSES	%%	PUT		%%	Actualizar almacenes"),
	SCL_WAREHOUSES_ACTIVE_LIST(		"WAREHOUSES	%%	GET		%%	Ver lista de alamcenes habilitados"),
	SCL_WAREHOUSES_INACTIVE_LIST(	"WAREHOUSES	%%	GET		%%	Ver lista de alamcenes deshabilitados"),
	SCL_WAREHOUSES_VIEW_BYID(		"WAREHOUSES	%%	GET		%%	Ver detalle de almacenes"),
	SCL_WAREHOUSES_ENABLE_BYID(		"WAREHOUSES	%%	PUT		%%	Habilitar o deshabilitar almacenes"),
	SCL_WAREHOUSES_EXISTS__BY(		"WAREHOUSES %% 	GET 	%%  Validar si existe un alamcén por campo" ),
	
	/* AREAS */
	SCL_AREAS_CREATE(						"AREAS	%%	POST	%%	Crear área"),
	SCL_AREAS_UPDATE_BYID(					"AREAS	%%	PUT		%%	Actualizar área"),
	SCL_AREAS_ACTIVE_LIST(					"AREAS	%%	GET		%%	Ver lista de áreas habilitadas"),
	SCL_AREAS_INACTIVE_LIST(				"AREAS	%%	GET		%%	Ver lista de área deshabilitadas"),
	SCL_AREAS_VIEW_BYID(					"AREAS	%%	GET		%%	Ver detalle de área"),
	SCL_AREAS_ENABLE_BYID(					"AREAS	%%	PUT		%%	Habilitar o deshabilitar área"),
	SCL_AREAS_EXISTS__BY(					"AREAS  %% 	GET 	%%  Validar si existe un área por campo" ),
	SCL_AREAS_ACTIVE__BY__WAREHOUSE_BYID(	"AREAS	%%	GET		%%	Ver lista de área habilitadas por almacén"),
	
	
	/* UBICACIOES */
	SCL_LOCATIONS_CREATE(				"LOCATIONS	%%	POST	%%	Crear ubicación"),
	SCL_LOCATIONS_UPDATE_BYID(			"LOCATIONS	%%	PUT		%%	Actualizar ubicación"),
	SCL_LOCATIONS_ACTIVE_LIST(			"LOCATIONS	%%	GET		%%	Ver lista de ubicaciones habilitadas"),
	SCL_LOCATIONS_INACTIVE_LIST(		"LOCATIONS	%%	GET		%%	Ver lista de ubicaciones deshabilitadas"),
	SCL_LOCATIONS_VIEW_BYID(			"LOCATIONS	%%	GET		%%	Ver detalle de ubicación"),
	SCL_LOCATIONS_ENABLE_BYID(			"LOCATIONS	%%	PUT		%%	Habilitar o deshabilitar ubicaciones"),
	SCL_LOCATIONS_EXISTS__BY(			"LOCATIONS 	%% 	GET 	%%  Validar si existe una ubicación por campo" ),
	SCL_LOCATIONS_BY__WAREHOUSE_BYID(	"LOCATIONS 	%% 	GET 	%% Consultar ubicaciones por almacén"),
	
	/* ORDENES DE COMPRA */
	SCL_PURCHASEORDERS_CREATE(				"PURCHASEORDERS %% POST %% Crear una orden de compra"),
	SCL_PURCHASEORDERS_UPDATE_BYID(			"PURCHASEORDERS %% PUT 	%% Actualizar una orden de compra"),
	SCL_PURCHASEORDERS_ENABLE_BYID(			"PURCHASEORDERS %% PUT 	%% Habilitar o deshabilitar una orden de compra"),
	SCL_PURCHASEORDERS_VIEW_BYID(			"PURCHASEORDERS %% GET 	%% Ver detalle de una orden de compra"),
	SCL_PURCHASEORDERS_VIEW_BY__CODE_BYCODE(	"PURCHASEORDERS %% GET 	%% Consultar orden de compra por folio"),
	SCL_PURCHASEORDERS_APPROVE_BYID(		"PURCHASEORDERS %% PUT 	%% Aprobar una orden de compra"),
	SCL_PURCHASEORDERS_ACTIVE_LIST(			"PURCHASEORDERS %% GET 	%% Ver lista de ordenes activas"),
	SCL_PURCHASEORDERS_INACTIVE_LIST(		"PURCHASEORDERS	%% GET 	%% Ver lista de ordenes inactivas"),
	SCL_PURCHASEORDERS_PDF_BYID(			"PURCHASEORDERS	%% GET 	%% Exportar pedido de compra a PDF"),
	
	SCL_MOVEMENTS_CREATE(					"MOVEMENTS %% POST 	%% Crear un movimiento"),
	SCL_MOVEMENTS_UPDATE_BYID(				"MOVEMENTS %% PUT 	%% Actualizar un movimiento"),
	SCL_MOVEMENTS_ENABLE_BYID(				"MOVEMENTS %% PUT 	%% Habilitar o deshabilitar un movimiento"),
	SCL_MOVEMENTS_VIEW_BYID(				"MOVEMENTS %% GET 	%% Ver detalle de un movimiento"),
	SCL_MOVEMENTS_ACTIVE_LIST(				"MOVEMENTS %% GET 	%% Ver lista de movimientos activos"),
	SCL_MOVEMENTS_INACTIVE_LIST(			"MOVEMENTS %% GET 	%% Ver lista de movimientos inactivos"),
	SCL_MOVEMENTS_BY__PURCHASEORDER_BYID(	"MOVEMENTS %% GET 	%% Ver lista de movimientos por orden de compra"),
	SCL_MOVEMENTS_CONFIRM_BYID(				"MOVEMENTS %% PUT 	%% Confirmar movimientos"),
	SCL_MOVEMENTS_CANCEL_BYID(				"MOVEMENTS %% PUT 	%% Cancelar movimientos"),
	
	
	/* SOLICITUDES AL ALMACÉN */
	SCL_CONSUMPTIONS_CREATE(			"CONSUMPTIONS %% POST   %% Crear solicitud de materiales al almacén"),
	SCL_CONSUMPTIONS_UPDATE_BYID(		"CONSUMPTIONS %% PUT    %% Actualizar solicitud al alamcén"),
	SCL_CONSUMPTIONS_VIEW_BYID(   		"CONSUMPTIONS %% GET 	%% Ver detalle de ordern interna"),
	SCL_CONSUMPTIONS_ACTIVE_LIST(		"CONSUMPTIONS %% GET    %% Ver lista de solicitudes activas de materiales al almacén"),
	SCL_CONSUMPTIONS_INACTIVE_LIST(		"CONSUMPTIONS %% GET 	%% Ver lista de solicitudes inactivas de materiales al almacén"),
	SCL_CONSUMPTIONS_ENABLE_BYID(		"CONSUMPTIONS %% PUT 	%% Habilitar o deshabilitar una solicitud interna"),
	SCL_CONSUMPTIONS_CONFIRM_BYID(		"CONSUMPTIONS %% PUT 	%% Confirmar una solicitud de material"),
	SCL_CONSUMPTIONS_APPROVE_BYID(		"CONSUMPTIONS %% PUT 	%% Aprobar una solicitud de material"),
	

	/* SERVICIOS */
	SCL_SERVICES_CREATE(			"SERVICES	%%	POST	%%	Crear servicio"),
	SCL_SERVICES_UPDATE_BYID(		"SERVICES	%%	PUT		%%	Actualizar servicio"),
	SCL_SERVICES_ACTIVE_LIST(		"SERVICES	%%	GET		%%	Ver lista de servicios habilitados"),
	SCL_SERVICES_INACTIVE_LIST(		"SERVICES	%%	GET		%%	Ver lista de servicios deshabilitados"),
	SCL_SERVICES_VIEW_BYID(			"SERVICES	%%	GET		%%	Ver detalle de servicio"),
	SCL_SERVICES_ENABLE_BYID(		"SERVICES	%%	PUT		%%	Habilitar o deshabilitar servicio"),
	SCL_SERVICES_EXISTS__BY(		"SERVICES   %% 	GET 	%%  Validar si existe un servicio por campo" ),
	
	/* SOLICITUDES DE SERVICIO AL ALMACÉN */
	SCL_SERVICEORDER_CREATE(			"SERVICEORDER %% POST   %% Crear solicitud de servicio"),
	SCL_SERVICEORDER_UPDATE_BYID(		"SERVICEORDER %% PUT    %% Actualizar solicitud de servicio"),
	SCL_SERVICEORDER_VIEW_BYID(   		"SERVICEORDER %% GET 	%% Ver detalle de solicitud de servicio"),
	SCL_SERVICEORDER_ACTIVE_LIST(		"SERVICEORDER %% GET    %% Ver lista de solicitudes solicitud de servicio habilitadas"),
	SCL_SERVICEORDER_INACTIVE_LIST(		"SERVICEORDER %% GET 	%% Ver lista de solicitudes solicitud de servicio deshabilitadas"),
	SCL_SERVICEORDER_ENABLE_BYID(		"SERVICEORDER %% PUT 	%% Habilitar o deshabilitar una solicitud de servicio"),
	SCL_SERVICEORDER_APPROVE_BYID( 		"SERVICEORDER %% PUT 	%% Aprobar orden de servicio"),
	SCL_SERVICEORDER_CONFIRM_BYID(		"SERVICEORDER %% PUT 	%% Confirmar una solicitud  de servicio"),
	
	
	/* ADMINISTRACIÓN DE LOTES DE PRODUCCIÓN */
	
	SCL_BATCH_CREATE(				"BATCHS %% POST  %% Crear registro de lote"),
	SCL_BATCH_UPDATE_BYID(			"BATCHS %% PUT   %% Actualizar lote"),
	SCL_BATCH_VIEW_BYID(			"BATCHS %% GET   %% Ver detalle de lote"),
	SCL_BATCH_VIEW_ENTRIES_BYID(	"BATCHS %% GET   %% Ver detalle de entradas de lote"),
	SCL_BATCH_VIEW_OUTPUTS_BYID(	"BATCHS %% GET   %% Ver detalle de salidas de lote"),
	SCL_BATCH_VIEW_REPORT_BYID(		"BATCHS %% GET   %% Ver reporte diario de lote"),
	SCL_BATCH_VIEW_ENTRIESBED_BYID(	"BATCHS %% GET   %% Ver entradas de cama "),
	SCL_BATCH_VIEW_OUTPUTSBED_BYID(	"BATCHS %% GET   %% Ver salidas de cama"),
	SCL_BATCH_ENABLE_BYID(			"BATCHS %% PUT   %% Habilitar o deshabilitar lote"),
	SCL_BATCH_ACTIVE_LIST(			"BATCHS %% GET   %% Ver lista de lotes habilitados"),
	SCL_BATCH_INACTIVE_LIST(		"BATCHS %% GET   %% Ver lista de lotes deshabilitados"),
	SCL_BATCH_EXISTS__BY(			"BATCHS %% GET   %% Validar si existe un lote por campo"),
	
	/* ENTRADAS DE PRODUCCION*/
	SCL_ENTRY_PRODUCT_BY__PRODUCTIVEUNIT_BYID( 			"ENTRIES %% POST  %% Registro de entrada de aves a caseta"),
	SCL_ENTRY_CONTENT_BY__STORAGEUNIT_BYID(				"ENTRIES %% POST   %% Registro de entrada de contenido de materi prima"),
	SCL_ENTRY_BED_BY__PRODUCTIVEUNIT_BYID(				"ENTRIES %% POST   %% Registro de entrada de cama"),
	
	/* SALIDAS DE PRODUCCION */
	SCL_OUTPUT_PRODUCT_BY__PRODUCTIVEUNIT_BYID(				"OUTPUTS %% POST  %% Registro de salida de producción"),
	SCL_OUTPUT_STATUS_BY__PRODUCTIVEUNIT_BYID(				"OUTPUTS %% GET  %% Ver cantidad existente de producción por caseta"),
	SCL_OUTPUT_STATUSBED_BY__PRODUCTIVEUNIT_BYID(			"OUTPUTS %% GET  %% Ver cantidad existente de cama por caseta"),
	SCL_OUTPUT_BED_BY__PRODUCTIVEUNIT_BYID(											"OUTPUTS %% POST   %% Registro de salida de cama"),
	
	/*REPORTES DE PRODUCCIÓN*/
	SCL_REPORT_PRODUCTION_BY__PRODUCTIVEUNIT_BYID(		"REPORT %% POST  %% Crear reporte de lote"),
	SCL_REPORT_CONTENT_BY__STORAGEUNIT_BYID(			"REPORT %% POST  %% Crear reporte de estato de unidad de almacenamiento"),
	
	
	/* INVENTARIOS */
	SCL_INVENTORIES_CREATE(				"INVENTORIES	%%	POST	%%	Crear inventarios"),
	SCL_INVENTORIES_UPDATE_BYID(		"INVENTORIES	%%	PUT		%%	Actualizar inventarios"),
	SCL_INVENTORIES_ACTIVE_LIST(		"INVENTORIES	%%	GET		%%	Ver lista de inventarios habilitados"),
	SCL_INVENTORIES_INACTIVE_LIST(		"INVENTORIES	%%	GET		%%	Ver lista de inventarios deshabilitados"),
	SCL_INVENTORIES_VIEW_BYID(			"INVENTORIES	%%	GET		%%	Ver detalle de inventarios"),
	SCL_INVENTORIES_ENABLE_BYID(		"INVENTORIES	%%	PUT		%%	Habilitar o deshabilitar inventarios"),
	
	/*CONFIGURACIONES*/
	SCL_CONFIG_PREFIXIES_LIST(		"CONFIGURATION	%%	GET	%%	Ver prefijos de datos maestros"),
	SCL_CONFIG_PREFIXIES_UPDATE(	"CONFIGURATION	%%	PUT	%%	Actualizar prefijos de datos maestros"),
	SCL_CONFIG_WAREHOUSES_MAIN_VIEW("CONFIGURATION	%%	GET	%%	Obtener el almacén principal configurado"),
	
	/*ENDPOINTS PÚBLICOS*/
	SCL_PUBLIC_OAUTH_TOKEN(				"PUBLIC %% 	POST 	%% 	Obtener token de seguridad"),
	SCL_PUBLIC_USERS_RESET_PASSWORD(	"PUBLIC	%%	PUT		%%	Generar nueva contraseña de usuario"),
	SCL_PUBLIC_USERS_RECOVER_PASSWORD(	"PUBLIC	%%	PUT		%%	Solicitar recuperación de contraseña"),
	
	
	/* Rutas ocupadas solo entre servicios del back*/
//	BACK_USERS_BY__USERNAME_BYUSERNAME(				"BACK_SCL	%%	GET		%%	Consultar usuario por username"),
//	BACK_PERMISSIONS_USERS_BYID(					"BACK_SCL	%%	GET		%%	Consultar lista de permisos por usuario"),
	
	/* Rutas ocupadas desde el PUBLIC sin inicio de sesión*/
	
//	PUBLIC_SECURITY_OAUTH_TOKEN(						"PUBLIC_SCL	%%	POST	%%	Generar o actualizar token de seguridad"),
//	
//	PUBLIC_PERMISSIONS_BYPREFIX_JSON (		"PUBLIC_SCL %% GET %% Generación dinámica de permisos en formato JSON"),
//	PUBLIC_PERMISSIONS_BYPREFIX_TRANSLATION ("PUBLIC_SCL %% GET %% Generación dinámica de permisos para traducción"),
//	PUBLIC_PERMISSIONS_BYPREFIX_PROPERTIES (	"PUBLIC_SCL %% GET %% Generación dinámica de permisos para el properties"),
	
	
	/*====================================================
	 * Permisos de la aplicación de SCL
	 * ====================================================*/
	
	/* LECTORES RFID */
//	SCL_READERS_CREATE(				"MONITOR	%%	POST	%%	Registrar lectores"),
//	SCL_READERS_UPDATE_BYID(		"MONITOR	%%	PUT		%%	Actualizar lectores"),
//	SCL_READERS_LIST(				"MONITOR	%%	GET		%%	Ver lista de  lectores"),
//	SCL_READERS_ENABLE_BYID(		"MONITOR	%%	PUT		%%	Habilitar o deshabilitar lectores"),
//	SCL_READERS_VIEW_BYID(			"MONITOR	%%	GET		%%	Ver detalle de un lector"),
//	SCL_READERS_EXISTS_BY__IP_BYIP(	"MONITOR	%%	GET		%%	Consultar si existe un lector por su IP para validación"),
//	SCL_READERS_ALERTS(				"MONITOR	%%	GET		%%	Recibir alertas vía email del módulo de RFID"),
//	SCL_INTERFACES_MONITOR(			"MONITOR	%%	GET		%%	Monitor de interfaces SCL"),
	
	
	
	
	/* DASHBOARD  de alamcén*/
//	SCL_DASHBOARDS_WAREHOUSE_STOCK_ANTIQUE(					"DASHBOARDS_WAREHOUSE	%%	GET	%%	Ver gráfico de antigüedad de rollos en el almacén"),
//	SCL_DASHBOARDS_WAREHOUSE_STOCK_MATERIALS(				"DASHBOARDS_WAREHOUSE	%%	GET	%%	Ver gráfico de materiales del almacén"),
//	SCL_DASHBOARDS_WAREHOUSE_STOCK_OCCUPATION(				"DASHBOARDS_WAREHOUSE	%%	GET	%%	Ver gráfico de ocupación del almacén"),
//	SCL_DASHBOARDS_WAREHOUSE_STOCK(							"DASHBOARDS_WAREHOUSE	%%	GET	%%	Ver gráfico del almacén"),
//	SCL_DASHBOARDS_WAREHOUSE_STOCK_BY__LOCATION_BYLGPLA(	"DASHBOARDS_WAREHOUSE	%%	GET	%%	Ver lista de rollos por ubicación"),
//	SCL_DASHBOARDS_WAREHOUSE_STOCK_BY__INTERVAL_BYFROM_BYTO("DASHBOARDS_WAREHOUSE	%%	GET	%%	Ver lista de rollos por intervalo de creación"),
//	SCL_DASHBOARDS_WAREHOUSE_STOCK_BY__MATERIAL_BYMATNR(	"DASHBOARDS_WAREHOUSE	%%	GET	%%	Ver lista de rollos por material"),
	  	
	/* Dashboard de entrada */
//	SCL_DASHBOARDS_ENTRANCE_OPEN__OTS(						"DASHBOARDS_ENTRANCE	%%	GET	%%	Ver lista de OTs activas de entrada"),
//	SCL_DASHBOARDS_ENTRANCE_OPEN__OTS_ANTIQUE(				"DASHBOARDS_ENTRANCE	%%	GET	%%	Ver gráfico de antigüedad de OTs activas de entrada"),
//	SCL_DASHBOARDS_ENTRANCE_CLOSE__OTS_HISTORY(				"DASHBOARDS_ENTRANCE	%%	GET	%%	Ver gráfico de historial de OTs cerradas de entrada"),
//	SCL_DASHBOARDS_ENTRANCE_OPEN__OT_BYID(					"DASHBOARDS_ENTRANCE	%%	GET	%%	Ver detalle de OT activa de entrada"),
//	SCL_DASHBOARDS_ENTRANCE_OPEN__OTS_INTERVAL_BYFROM_BYTO(	"DASHBOARDS_ENTRANCE	%%	GET	%%	Ver lista de OTs activas de entrada por intervalo"),
		  
	/* Dashboard de salida */
//	SCL_DASHBOARDS_DELIVERIES_ACTIVE(					"DASHBOARDS_DELIVERIES	%%	GET	%%	Ver lista de embarques activos"),
//	SCL_DASHBOARDS_DELIVERIES_ANTIQUE(					"DASHBOARDS_DELIVERIES	%%	GET	%%	Ver gráfico de antigüedad de embarques activos"),
//	SCL_DASHBOARDS_DELIVERIES_CLOSE_HISTORY(			"DASHBOARDS_DELIVERIES	%%	GET	%%	Ver gráfico de historial de embarques cerrados"),
//	SCL_DASHBOARDS_DELIVERIES_BY__INTERVAL_BYFROM_BYTO(	"DASHBOARDS_DELIVERIES	%%	GET	%%	Ver embarques activas por intervalos"),
//	SCL_DASHBOARDS_DELIVERIES_CLOSE__OT_BYVBELN(		"DASHBOARDS_DELIVERIES	%%	GET	%%	Ver detalle de OT cerrada"),
	
	/* CONFIGURACION */
//	SCL_CONFIG_DASHBOARDS_INDICATORS_VIEW(		"CONFIGURATION	%%	PUT	%%	Ver indicadores de dashboards"),
//	SCL_CONFIG_DASHBOARDS_INDICATORS_UPDATE(	"CONFIGURATION	%%	PUT	%%	Actualizar indicadores de dashboards"),
//	SCL_CONFIG_RFID_INDICATORS_VIEW(			"CONFIGURATION	%%	PUT	%%	Ver indicadores de RFID"),
//	SCL_CONFIG_RFID_INDICATORS_UPDATE(			"CONFIGURATION	%%	PUT	%%	Actualizar indicadores de RFID"),
//	SCL_CONFIG_REFRESH_STOCK(					"CONFIGURATION	%%	PUT	%%	Refrescar el inventario actual desde SAP"),
//	SCL_CONFIG_REFRESH_OTS(						"CONFIGURATION	%%	PUT	%%	Refrescar las OTs activas desde SAP"),
//	SCL_CONFIG_REFRESH_TRUCKS(					"CONFIGURATION	%%	PUT	%%	Refrescar el catálogo de usuarios de montacargas desde SAP"),
//	SCL_CONFIG_REFRESH_DELIVERIES (				"CONFIGURATION	%%	PUT	%%	Refrescar los embarques activos desde SAP"),
			
	/* INVENTORIES */
//	SCL_INVENTORIES_LIST(		"INVENTORIES	%%	GET	%%	Ver lista de inventarios físicos"),
//	SCL_INVENTORIES_VIEW_BYID(	"INVENTORIES	%%	GET	%%	Ver detalle de inventario físico"),
//	SCL_INVENTORIES_SEND(		"INVENTORIES	%%	PUT	%%	Enviar inventario físico a SAP"),
			
	/* Tareas de MTC */
//	SCL_TASKS_CREATE(			"TASKS	%%	POST	%%	Crear tareas"),
//	SCL_TASKS_UPDATE_BYIDTSK(	"TASKS	%%	PUT		%%	Actualizar tareas"),
//	SCL_TASKS_ENABLE_BYIDTSK(	"TASKS	%%	PUT		%%	Cancelar tareas"),
//	SCL_TASKS_ACTIVE_COUNT(		"TASKS	%%	GET		%%	Ver Número de tareas activas"),
//	SCL_TASKS_LIST(				"TASKS	%%	GET		%%	Ver lista de tareas activas"),
//	SCL_TASKS_TRUCKS_LIST(		"TASKS	%%	GET		%%	Ver lista de usuarios de montacargas"),
	
	/* Registros de OPC */
//	SCL_OPC_CORELINK_CREATE(		"OPC	%%	POST	%%	Crear registros manuales de Corelink"),
//	SCL_OPC_CORELINK_LIST(			"OPC	%%	GET		%%	Ver lista de registros de Corelink"),
//	SCL_OPC_CORELINK_UPDATE_BYID(	"OPC	%%	PUT		%%	Actualizar registro de Corelink"),
//	SCL_OPC_WINDER_CREATE(			"OPC	%%	POST	%%	Crear registros manuales de Winder"),
//	SCL_OPC_WINDER_LIST(			"OPC	%%	GET		%%	Ver lista de registros de Winder"),
//	SCL_OPC_WINDER_UPDATE_BYID(		"OPC	%%	PUT		%%	Actualizar registro de Winder"),
		  
	 
	/* Rutas ocupadas solo entre servicios del back*/
//	SCL_BACK_OPC_VOLUME(								"BACK_SCL	%%	PUT		%%	Ejecutar script de volumen OPC"),
//	SCL_BACK_OPC_UA__FOR__RECORD(						"BACK_SCL	%%	PUT		%%	Consultar a OPC siguiente bobina para grabar"),
//	SCL_BACK_OPC_RECORDED__UA(							"BACK_SCL	%%	PUT		%%	Informar a OPC de UA grabada con RFID"),
//	SCL_BACK_INVENTORIES_SCANNED__RFID(					"BACK_SCL	%%	PUT		%%	Ejecutar inventario físico con RFID"),
//	SCL_BACK_INTERFACES_REPORT__TO__SAP__ROLL__IN__ARC(	"BACK_SCL	%%	PUT		%%	Informar a SAP bobina detectada en arco RFID"),
		
	/*====================================================
	 * Permisos de la aplicación de Cuestinarios
	 * ====================================================*/
	
//	SURVEY_DASHBOARDS_WE_DIMENSIONS(	"DASHBAORD_WORKENVIRONMENT %% GET %% Ver gráfico de clima laboral por dimensión"),
//	SURVEY_DASHBOARDS_WE_FACTORS(		"DASHBAORD_WORKENVIRONMENT %% GET %% Ver gráfico de clima laboral por factor");
	
	
;
	
	
    private String description;

    PermissionEnum(String description) {
        this.description = description;
    }
    
    public boolean token() {
    	return !name().contains("PUBLIC".toUpperCase());
    }
    
    public String module() {
    	String[] parts = description.split("%%");
    	return parts[0].trim();
    }
    
    public String method() {
    	String[] parts = description.split("%%");
    	return parts[1].toLowerCase().trim();
    }

    public String description() {
    	String[] parts = description.split("%%");
    	try {
    		return parts[2].trim();
		} catch (Exception e) {
			return "";
		}
    }
    
    public String path() {
    	
    	String ENABLE = "enable";
    	String STATUS = "{status:enable|disable}";
    	String CLEAN = "rfid|survey|public|view|update|create|list|pls|play|all".toLowerCase();
    	
		StringBuilder stringBuilder = new StringBuilder("/");
		
		stringBuilder.append(
			name().toLowerCase()
			.replaceAll("__", "-")
			.replaceAll(CLEAN, "")
			.replaceAll("_","/")
		);
		int ENABLEstart = stringBuilder.indexOf(ENABLE);
		if(ENABLEstart>=0) {
			stringBuilder.delete(ENABLEstart, ENABLEstart+ENABLE.length()).insert(ENABLEstart, STATUS);
		}
		
		
		stringBuilder
		.replace(0, stringBuilder.length(), stringBuilder.toString().replaceAll("//", "/"))
		.replace(0, stringBuilder.length(), stringBuilder.toString().replaceAll("//", "/"))
		.replace(0, stringBuilder.length(), stringBuilder.toString().replaceAll("by([a-z]+)", "{$1}"));
	
		if(stringBuilder.toString().endsWith("/")) {
			stringBuilder.replace(0, stringBuilder.length(), stringBuilder.subSequence(0, stringBuilder.length()-1).toString());
		}
		
		return stringBuilder.toString();
    }

}
