<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	var search_type_repository = "none";
	var search_keyWord = "";
	var selectID;

	$(function() {
		optionAction();
		searchAction();
		repositoryListInit();
		bootstrapValidatorInit();

		addRepositoryAction();
		editRepositoryAction();
		deleteRepositoryAction();
	})

	// 下拉框选择动作
	function optionAction() {
		$(".dropOption").click(function() {
			var type = $(this).text();
			$("#search_input").val("");
			if (type == "所有") {
				$("#search_input").attr("readOnly", "true");
				search_type_repository = "searchAll";
			} else if (type == "仓库ID") {
				$("#search_input").removeAttr("readOnly");
				search_type_repository = "searchByID";
			} else if (type == "仓库地址") {
				$("#search_input").removeAttr("readOnly");
				search_type_repository = "searchByAddress";
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
	function repositoryListInit() {
		$('#repositoryList')
				.bootstrapTable(
						{
							columns : [
									{
										field : 'repoId',
										title : '仓库ID'
									//sortable: true
									},
									{
										field : 'repoAddress',
										title : '仓库地址'
									},
									{
										field : 'repoAdmin',
										title : '仓库管理员'
									},
									{
										field : 'repoStatus',
										title : '状态'
									},
									{
										field : 'repoArea',
										title : '面积',
										visible : false
									},
									{
										field : 'repoDesc',
										title : '描述',
										visible : false
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
												selectID = row.id;
												rowEditOperation(row);
											},
											'click .delete' : function(e,
													value, row, index) {
												selectID = row.id;
												$('#deleteWarning_modal')
														.modal('show');
											}
										}
									} ],
							url : 'repositoryManage/getRepositoryList',
							method : 'GET',
                            queryParams : function(params){	// 分页查询参数
                                return {
                                    offset: params.offset,
                                    limit: params.limit,
                                    searchType: search_type_repository,
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
		$('#repositoryList').bootstrapTable('refresh', {
			query : {}
		});
	}

	// 行编辑操作
	function rowEditOperation(row) {
		$('#edit_modal').modal("show");

		// load info
		$('#repository_form_edit').bootstrapValidator("resetForm", true);
		$('#repository_address_edit').val(row.address);
		$('#repository_status_edit').val(row.status);
		$('#repository_area_edit').val(row.area);
		$('#repository_desc_edit').val(row.desc);
	}

	// 添加仓库模态框数据校验
	function bootstrapValidatorInit() {
		$("#repository_form,#repository_form_edit").bootstrapValidator({
			message : 'This is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			excluded : [ ':disabled' ],
			fields : {
				repository_address : {
					validators : {
						notEmpty : {
							message : '仓库地址不能为空'
						}
					}
				},
				repository_status : {
					validators : {
						notEmpty : {
							message : '仓库状态不能为空'
						}
					}
				},
				repository_area : {
					validators : {
						notEmpty : {
							message : '仓库面积不能为空'
						}
					}
				}
			}
		})
	}

	// 编辑仓库信息
	function editRepositoryAction() {
		$('#edit_modal_submit').click(
				function() {
					$('#repository_form_edit').data('bootstrapValidator')
							.validate();
					if (!$('#repository_form_edit').data('bootstrapValidator')
							.isValid()) {
						return;
					}

					var data = {
						id : selectID,
						address : $('#repository_address_edit').val(),
						status : $('#repository_status_edit').val(),
						area : $('#repository_area_edit').val(),
						desc : $('#repository_desc_edit').val(),
					}

					// ajax
					$.ajax({
						type : "POST",
						url : 'repositoryManage/updateRepository',
						dataType : "json",
						contentType : "application/json",
						data : JSON.stringify(data),
						success : function(response) {
							$('#edit_modal').modal("hide");
							var type;
							var msg;
							if (response.result == "success") {
								type = "success";
								msg = "仓库信息更新成功";
							} else if (resposne == "error") {
								type = "error";
								msg = "仓库信息更新失败"
							}
							infoModal(type, msg);
							tableRefresh();
						},
						error : function(response) {
						}
					});
				});
	}

	// 刪除仓库信息
	function deleteRepositoryAction() {
		$('#delete_confirm').click(function() {
			var data = {
				"repositoryID" : selectID
			}

			// ajax
			$.ajax({
				type : "GET",
				url : "repositoryManage/deleteRepository",
				dataType : "json",
				contentType : "application/json",
				data : data,
				success : function(response) {
					$('#deleteWarning_modal').modal("hide");
					var type;
					var msg;
					if (response.result == "success") {
						type = "success";
						msg = "仓库信息删除成功";
					} else {
						type = "error";
						msg = "仓库信息删除失败";
					}
					infoModal(type, msg);
					tableRefresh();
				},
				error : function(response) {
				}
			})

			$('#deleteWarning_modal').modal('hide');
		})
	}

	// 添加仓库信息
	function addRepositoryAction() {
		$('#add_repository').click(function() {
			$('#add_modal').modal("show");
		});

		$('#add_modal_submit').click(function() {
			var data = {
				address : $('#repository_address').val(),
				status : $('#repository_status').val(),
				area : $('#repository_area').val(),
				desc : $('#repository_desc').val(),
			}
			// ajax
			$.ajax({
				type : "POST",
				url : "repositoryManage/addRepository",
				dataType : "json",
				contentType : "application/json",
				data : JSON.stringify(data),
				success : function(response) {
					$('#add_modal').modal("hide");
					var msg;
					var type;
					if (response.result == "success") {
						type = "success";
						msg = "仓库添加成功";
					} else if (response.result == "error") {
						type = "error";
						msg = "仓库添加失败";
					}
					infoModal(type, msg);
					tableRefresh();

					// reset
					$('#repository_address').val("");
					$('#repository_ststus').val("");
					$('#repository_area').val("");
					$('#repository_desc').val("");
					$('#repository_form').bootstrapValidator("resetForm", true);
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
		<li>仓库信息管理</li>
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
						<li><a href="javascript:void(0)" class="dropOption">仓库ID</a></li>
						<li><a href="javascript:void(0)" class="dropOption">仓库地址</a></li>
						<li><a href="javascript:void(0)" class="dropOption">所有</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-9 col-sm-9">
				<div>
					<div class="col-md-3 col-sm-4">
						<input id="search_input" type="text" class="form-control"
							placeholder="仓库信息查询">
					</div>
					<div class="col-md-2 col-sm-2">
						<button id="search_button" class="btn btn-success">
							<span class="glyphicon glyphicon-search"></span> <span>查询</span>
						</button>
					</div>
				</div>
			</div>
		</div>

		<div class="row" style="margin-top: 25px">
			<div class="col-md-5">
				<button class="btn btn-sm btn-default" id="add_repository">
					<span class="glyphicon glyphicon-plus"></span> <span>添加仓库信息</span>
				</button>
				<button class="btn btn-sm btn-default" id="import_repository">
					<span class="glyphicon glyphicon-import"></span> <span>导入</span>
				</button>
				<button class="btn btn-sm btn-default" id="export_repository">
					<span class="glyphicon glyphicon-export"></span> <span>导出</span>
				</button>
			</div>
			<div class="col-md-5"></div>
		</div>

		<div class="row" style="margin-top: 15px">
			<div class="col-md-12">
				<table id="repositoryList" class="table table-striped"></table>
			</div>
		</div>
	</div>
</div>

<!-- 添加仓库信息模态框 -->
<div id="add_modal" class="modal fade" table-index="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">添加仓库信息</h4>
			</div>
			<div class="modal-body">
				<!-- 模态框的内容 -->
				<div class="row">
					<div class="col-md-1 col-sm-1"></div>
					<div class="col-md-8 col-sm-8">
						<form class="form-horizontal" role="form" id="repository_form"
							style="margin-top: 25px">
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>仓库地址：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="repository_address"
										name="repository_address" placeholder="仓库地址">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>仓库面积：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="repository_area"
										name="repository_area" placeholder="仓库面积">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>仓库状态：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="repository_status"
										name="repository_status" placeholder="仓库状态">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>仓库描述：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<textarea class="form-control" id="repository_desc"
										name="repository_desc" placeholder="仓库描述" style="min-width: 100%"></textarea>
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
						<h3>是否确认删除该条仓库信息</h3>
						<p>(注意：若该仓库已经有出入库记录或仓存信息，则该仓库信息将不能删除成功。如需删除该仓库的信息，请保证该仓库没有出入库和仓存信息关联)</p>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" type="button" data-dismiss="modal">
					<span>取消</span>
				</button>
				<button class="btn btn-danger" type="button" id="delete_confirm">
					<span>确认删除</span>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 编辑仓库信息模态框 -->
<div id="edit_modal" class="modal fade" table-index="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">编辑仓库信息</h4>
			</div>
			<div class="modal-body">
				<!-- 模态框的内容 -->
				<div class="row">
					<div class="col-md-1 col-sm-1"></div>
					<div class="col-md-8 col-sm-8">
						<form class="form-horizontal" role="form" id="repository_form_edit"
							style="margin-top: 25px">
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>仓库地址：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control" id="repository_address_edit"
										name="repository_address" placeholder="仓库地址">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>仓库面积：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control"
										id="repository_area_edit" name="repository_area"
										placeholder="仓库面积">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>仓库状态：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<input type="text" class="form-control"
										id="repository_status_edit" name="repository_status"
										placeholder="仓库状态">
								</div>
							</div>
							<div class="form-group">
								<label for="" class="control-label col-md-4 col-sm-4"> <span>仓库描述：</span>
								</label>
								<div class="col-md-8 col-sm-8">
									<textarea class="form-control"
										id="repository_desc_edit" name="repository_desc"
										placeholder="仓库描述"></textarea>
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