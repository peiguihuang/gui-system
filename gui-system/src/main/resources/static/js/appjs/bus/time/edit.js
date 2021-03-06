$().ready(function () {
    validateRule();
    selectLoad();
});

$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});



function selectLoad() {
    var html = "";
    $.ajax({
        url : '/bus/line/all',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                if ($("#busLineIdNew").val() == data[i].id){
                    html += '<option value="' + data[i].id + '" selected="selected">' + data[i].startPosition + ' -> ' + data[i].endPosition + '</option>'
                }else {
                    html += '<option value="' + data[i].id + '">' + data[i].startPosition + ' -> ' + data[i].endPosition +  '</option>'
                }
            }
            $("#busLineId").append(html);
            $("#busLineId").chosen({
                maxHeight : 200
            });
            //点击事件
            $('.chosen-select').on('change', function(e, params) {
                console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected,
                    }
                }
                $('#exampleTable').bootstrapTable('refresh', opt);
            });
        }
    });
}
function update() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/bus/time/update",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            name: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + "请输入名字"
            }
        }
    })
}