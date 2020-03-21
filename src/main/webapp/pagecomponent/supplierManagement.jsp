<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	var search_type_supplier = "none";
	var search_keyWord = "";
	var selectID;

	$(function() {
		optionAction();
		searchAction();
		supplierListInit();
		bootstrapValidatorInit();

		addSupplierAction();
		editSupplierAction();
		deleteSupplierAction();
	})

	// 下拉框选择动作
	function optionAction() {
		$(".dropOption").click(function() {
			var type = $(this).text();
			$("#search_input").val("");
			if (type == "所有") {
				$("#search_input").attr("readOnly", "true");
				search_type_supplier = "searchAll";
			} else if (type == "供应商ID") {
				$("#search_input").removeAttr("readOnly");
				search_type_supplier = "searchByID";
			} else if (type == "供应商名称") {
				$("#search_input").removeAttr("readOnly");
				search_type_supplier = "searchByName";
			} else {
				$("#search_input").removeAttr("readOnly");
			}

			$("#search_type").text(type);
			$("#search_input").attr("placeholder", type);
		})
	}

	// 搜索动作
	function searchAction() {
		$('#search_button').click(function() {
			search_keyWord = $('#search_input').val();
			tableRefresh();
		})
	}

	// 表格初始化
	function supplierListInit() {
		$('#supplierList')
				.bootstrapTable(
						{
							columns : [
									{
										field : 'supplierId',
										title : '供应商ID'
									},
									{
										field : 'supplierName',
										title : '供应商名称'
									},
									{
										field : 'supplierPerson',
										title : '负责人'
									},
									{
										field : 'supplierTel',
										title : '联系电话'
									},
									{
										field : 'supplierAddress',
										title : '地址',
										/*visible : false*/
									},
									{
										field : 'supplierEmail',
										title : '电子邮件',
										/*visible : false*/
									},
									{
										field : 'operation',
										title : '操作',
										formatter : function(value, row, index) {
											var s = '<button class="btn btn-info btn-sm edit"><span>编辑</span></button>';
											var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
											var fun = '';
											return s + ' ' + d;
										},
										events : {
											// 操作列中编辑按钮的动作
											'click .edit' : function(e, value,
													row, index) {
												selectID = row.supplierId;
												rowEditOperation(row);
											},
											'click .delete' : function(e,
													value, row, index) {
												selectID = row.supplierId;
												$('#deleteWarning_modal').modal(
														'show');
											}
										}
									} ],
							url : 'supplierManage/getSupplierList',
							method : 'GET',
							queryParams : function(params){	// 分页查询参数
                                return {
                                    offset: params.offset,
                                    limit: params.limit,
                                    searchType: search_type_supplier,
                                    keyWord: search_keyWord
                                };
							},
                            sidePagination : "server",	// 服务端分页
                            queryParamsType : "limit",
                            dataType : 'json',
                            pagination : true,
                            pageNumber : 1,
                            pageSize : 5,
                            pageList : [ 5, 10, 25, 50, 100 ],  // 可选的每页数据
                            paginationPreText: ' 上一页',
                            paginationNextText: '下一页',
						});
	}

	// 表格刷新
	function tableRefresh() {
		$('#supplierList').bootstrapTable('refresh', {
			query : {}
		});
	}

	// 行编辑操作
	function rowEditOperation(row) {
		$('#edit_modal').modal("show");

		// load info
		$('#supplier_form_edit').bootstrapValidator("resetForm", true);
		$('#supplier_name_edit').val(row.supplierName);
		$('#supplier_person_edit').val(row.supplierPerson);
		$('#supplier_tel_edit').val(row.supplierTel);
		$('#supplier_email_edit').val(row.supplierEmail);
		$('#supplier_address_edit').val(row.supplierAddress);
	}

	// 添加供应商模态框数据校验
	function bootstrapValidatorInit() {
		$("#supplier_form,#supplier_form_edit").bootstrapValidator({
			message : 'This is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			excluded : [ ':disabled' ],
			fields : {
				supplier_name : {
					validators : {
						notEmpty : {
							message : '供应商名称不能为空'
						}
					}
				},
				supplier_tel : {
					validators : {
						notEmpty : {
							message : '供应商电话不能为空'
						}
					}
				},
				supplier_email : {
					validators : {
						notEmpty : {
							message : '供应商E-mail不能为空'
						},
						regexp : {
							regexp : '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
							message : 'E-mail的格式不正确'
						}
					}
				},
				supplier_address : {
					validators : {
						notEmpty : {
							message : '供应商地址不能为空'
						}
					}
				},
				supplier_person : {
					validators : {
						notEmpty : {
							message : '供应商负责人不能为空'
						}
					}
				}
			}
		})
	}

	// 编辑供应商信息
	function editSupplierAction() {
		$('#edit_modal_submit').click(
				function() {
					$('#supplier_form_edit').data('bootstrapValidator')
							.validate();
					if (!$('#supplier_form_edit').data('bootstrapValidator')
							.isValid()) {
						return;
					}

					var data = {
						supplierId : selectID,
						supplierName : $('#supplier_name_edit').val(),
						supplierPerson : $('#supplier_person_edit').val(),
						supplierTel : $('#supplier_tel_edit').val(),
						supplierEmail : $('#supplier_email_edit').val(),
						supplierAddress : $('#supplier_address_edit').val()
					}

					// ajax
					$.ajax({
						type : "POST",
						url : 'supplierManage/updateSupplier',
						dataType : "json",
						contentType : "application/json",
						data : JSON.stringify(data),
						success : function(response) {
							$('#edit_modal').modal("hide");
							var type;
							var msg;
							if (response.result == "success") {
								type = "success";
								msg = "供应商信息更新成功";
							} else if (response.result == "error") {
								type = "error";
								msg = "供应商信息更新失败"
							}
							infoModal(type, msg);
							tableRefresh();
						},
						error : function(response) {
						}
					});
				});
	}

	// 刪除供应商信息
	function deleteSupplierAction(){
		$('#delete_confirm').click(function(){
			var data = {
				"supplierID" : selectID
			}
			
			// ajax
			$.ajax({
				type : "POST",
				url : "supplierManage/deleteSupplier/" + selectID,
				dataType : "json",
				contentType : "application/json",
				data : data,
				success : function(response){
					$('#deleteWarning_modal').modal("hide");
					var type;
					var msg;
					if(response.result == "success"){
						type = "success";
						msg = "供应商信息删除成功";
					}else{
						type = "error";
						msg = "供应商信息删除失败";
					}
					infoModal(type, msg);
					tableRefresh();
				},error : function(response){
				}
			})
			
			$('#deleteWarning_modal').modal('hide');
		})
	}

	// 添加供应商信息
	function addSupplierAction() {
		$('#add_supplier').click(function() {
			$('#add_modal').modal("show");
		});

		$('#add_modal_submit').click(function() {
			var data = {
				supplierName : $('#supplier_name_insert').val(),
				supplierPerson : $('#supplier_person_insert').val(),
				supplierTel : $('#supplier_tel_insert').val(),
				supplierEmail : $('#supplier_email_insert').val(),
				supplierAddress : $('#supplier_address_insert').val()
			}
			// ajax
			$.ajax({
				type : "POST",
				url : "supplierManage/updateSupplier",
				dataType : "json",
				contentType : "application/json",
				data : JSON.stringify(data),
				success : function(response) {
					$('#add_modal').modal("hide");
					var msg;
					var type;
					if (response.result == "success") {
						type = "success";
						msg = "供应商添加成功";
					} else if (response.result == "error") {
						type = "error";
						msg = "供应商添加失败";
					}
					infoModal(type, msg);
					tableRefresh();

					// reset
					$('#supplier_name_insert').val("");
					$('#supplier_person_insert').val("");
					$('#supplier_tel_insert').val("");
					$('#supplier_email_insert').val("");
					$('#supplier_address_insert').val("");
					$('#supplier_form').bootstrapValidator("resetForm", true);
				},
				error : function(response) {
				}
			})
		})
	}
	
	// 操作结果提示模态框
	function infoModal(type, msg) {
		$('#info_success').removeClass("hide");
		$('#info_error').removeClass("hide");
		if (type == "success") {
			$('#info_error').addClass("hide");
		} else if (type == "error") {
			$('#info_success').addClass("hide");
		}
		$('#info_content').text(msg);
		$('#info_modal').modal("show");
	}
</script>
<div class="panel panel-default">
	<ol class="breadcrumb">
		<li>供应商信息管理</li>
	</ol>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-1 col-sm-2">
				<div class="btn-group">
					<button class="btn btn-default dropdown-toggle"
						data-toggle="dropdown">
						<span id="search_type">查询方式</span> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="javascript:void(0)" class="dropOption">供应商ID</a></li>
						<li><a href="javascript:void(0)" class="dropOption">供应商名称</a></li>
						<li><a href="javascript:void(0)" class="dropOption">所有</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-9 col-sm-9">
				<div>
					<div class="col-md-3 col-sm-4">
						<input id="search_input" type="text" class="form-control"
							placeholder="供应商信息查询">
					</div>
					<div class="col-md-2 col-sm-3">
						<button id="search_button" class="btn btn-success">
							<span class="glyphicon glyphicon-search"></span> <span>查询</span>
						</button>
					</div>
				</div>
			</div>
		</div>

		<div class="row" style="margin-top: 25px">
			<div class="col-md-5">
				<button class="btn btn-sm btn-default" id="add_supplier">
					<span class="glyphicon glyphicon-plus"></span> <span>添加供应商</span>
				</button>
			</div>
			<div class="col-md-5"></div>
		</div>

		<div class="row" style="margin-top: 15px">
			<div class="col-md-12">
				<table id="supplierList" class="table table-striped"></table>
			</div>
		</div>
	</div>
</div>

<!-- 添加供应商信息模态框 -->
<div id="add_modal" class="modal fade" table-index="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">添加供应商信息</h4>
			</div>
			<div class="modal-body">
				<!-- 模态框的内容 -->
				<div class="row">
					<div class="col-md-1 col-sm-1"></div>
					<div class="col-md-8 col-sm-8">
						<form class="form-horizontal" role="form" id="supplier_form"
							style="margin-top: 25px">
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>供应商名称：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="supplier_name_insert"
										name="supplier_name" placeholder="供应商名称">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>负责人姓名：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="supplier_person_insert"
										name="supplier_person" placeholder="负责人姓名">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>联系电话：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="supplier_tel_insert"
										name="supplier_tel" placeholder="联系电话">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>电子邮件：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="supplier_email_insert"
										name="supplier_email" placeholder="电子邮件">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>联系地址：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="supplier_address_insert"
										name="supplier_address" placeholder="联系地址">
								</div>
							</div>
						</form>
					</div>
					<div class="col-md-1 col-sm-1"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" type="button" data-dismiss="modal">
					<span>取消</span>
				</button>
				<button class="btn btn-success" type="button" id="add_modal_submit">
					<span>提交</span>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 提示消息模态框 -->
<div class="modal fade" id="info_modal" table-index="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">信息</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-4 col-sm-4"></div>
					<div class="col-md-4 col-sm-4">
						<div id="info_success" class=" hide" style="text-align: center;">
							<img src="media/icons/success-icon.png" alt=""
								style="width: 100px; height: 100px;">
						</div>
						<div id="info_error" style="text-align: center;">
							<img src="media/icons/error-icon.png" alt=""
								style="width: 100px; height: 100px;">
						</div>
					</div>
					<div class="col-md-4 col-sm-4"></div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-4 col-sm-4"></div>
					<div class="col-md-4 col-sm-4" style="text-align: center;">
						<h4 id="info_content"></h4>
					</div>
					<div class="col-md-4 col-sm-4"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" type="button" data-dismiss="modal">
					<span>&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;</span>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 删除提示模态框 -->
<div class="modal fade" id="deleteWarning_modal" table-index="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">警告</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-3 col-sm-3" style="text-align: center;">
						<img src="media/icons/warning-icon.png" alt=""
							style="width: 70px; height: 70px; margin-top: 20px;">
					</div>
					<div class="col-md-8 col-sm-8">
						<h3>是否确认删除该条供应商信息</h3>
						<p>(注意：若该供应商已经有仓库入库记录，那么和该供应商有关的入库信息也将一并删除！)</p>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" type="button" data-dismiss="modal">
					<span>取消</span>
				</button>
				<button class="btn btn-danger" type="button" id="delete_confirm")>
					<span>确认删除</span>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 编辑供应商信息模态框 -->
<div id="edit_modal" class="modal fade" table-index="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">编辑供应商信息</h4>
			</div>
			<div class="modal-body">
				<!-- 模态框的内容 -->
				<div class="row">
					<div class="col-md-1 col-sm-1"></div>
					<div class="col-md-8 col-sm-8">
						<form class="form-horizontal" role="form" id="supplier_form_edit"
							style="margin-top: 25px">
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>供应商名称：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="supplier_name_edit"
										name="supplier_name" placeholder="供应商名称">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>负责人姓名：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control"
										id="supplier_person_edit" name="supplier_person"
										placeholder="负责人姓名">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>联系电话：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="supplier_tel_edit"
										name="supplier_tel" placeholder="联系电话">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>电子邮件：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control"
										id="supplier_email_edit" name="supplier_email"
										placeholder="电子邮件">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>联系地址：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control"
										id="supplier_address_edit" name="supplier_address"
										placeholder="联系地址">
								</div>
							</div>
						</form>
					</div>
					<div class="col-md-1 col-sm-1"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" type="button" data-dismiss="modal">
					<span>取消</span>
				</button>
				<button class="btn btn-success" type="button" id="edit_modal_submit">
					<span>确认更改</span>
				</button>
			</div>
		</div>
	</div>
</div>