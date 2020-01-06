$().ready(function () {
    validateRule();
    selectLoad();
    objChange();

});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function objChange() {

    $('input[type=radio][name=type]').change(function () {
        if (this.value == '0') {
            $("#merchantDiv").hide();
            $("#deviceDiv").hide();

        } else if (this.value == '1') {
            $("#merchantDiv").show();
            $("#deviceDiv").hide();

        }else if (this.value == '2') {
            $("#merchantDiv").hide();
            $("#deviceDiv").show();
        }
    });
    $("input[name=type]:eq(0)").prop("checked",'checked');

}

function selectLoad() {
    initApp();
    initMerchant();
    initMerchantDevice();
}


function initApp() {
    var html = "";
    $.ajax({
        url: '/device/appVerison/all',
        success: function (data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].appShowVersion + '</option>'
            }
            $("#appId").append(html);
            $("#appId").chosen({
                maxHeight: 200
            });
            //点击事件
            $('#appId').on('change', function (e, params) {
                console.log(params.selected);
                var opt = {
                    query: {
                        type: params.selected,
                    }
                }
            });
        }
    });
}


function initMerchant() {
    var html = "";
    $.ajax({
        url: '/device/merchant/all',
        success: function (data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].merchantName + '</option>'
            }
            $("#merchantId").append(html);
            $("#merchantId").chosen({
                maxHeight: 200
            });
            //点击事件
            $('#merchantId').on('change', function (e, params) {
                console.log(params.selected);
                var opt = {
                    query: {
                        type: params.selected,
                    }
                }
            });
        }
    });
}

function initMerchantDevice() {
    var html = "";
    $.ajax({
        url: '/device/merchantDevice/all',
        success: function (data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].deviceName + '</option>'
            }
            $("#deviceId").append(html);
            $("#deviceId").chosen({
                maxHeight: 200
            });
            //点击事件
            $('#deviceId').on('change', function (e, params) {
                console.log(params.selected);
                var opt = {
                    query: {
                        type: params.selected,
                    }
                }
            });
        }
    });
}

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/device/appDevice/save",
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
                required: icon + "请输入姓名"
            }
        }
    })
}