(function (ks, $) {

    var staffName = 'Jensen';

    // var API_DOMAIN = "https://9a0f14c2.ngrok.io";
    var API_DOMAIN = "http://localhost:8080";

    $(document).ready(function() {
        ks.caseRecord = new ks.CaseRecord();
        ko.applyBindings(ks.caseRecord, document.getElementById('theCaseRecord'));
        ks.caseRecord.retrieveListing();
    });


    ks.caseRecord = null;
    ks.CaseRecord = function() {
        var s = this;
        s.caseRecords = ko.observableArray([]);
        s.selectedRecord = ko.observable();
        s.selectedRefNum = ko.observable('');

        s.staffName = ko.observable(staffName);

        s.retrieveListing = function() {

            $.ajax({
                url: API_DOMAIN + '/konsulta/cases',
                contentType: 'application/json', dataType: 'json', type: 'GET', cache: false,
                success : function(data) {
                    if(data) {
                        $.each(data, function(idx, info) {
                            var caseRecordSimple = new ks.CaseRecordSimple(s);
                            caseRecordSimple.init(info);
                            s.caseRecords.push(caseRecordSimple);
                        });
                    }
                }
            });

            //harcoded code
            // $.each(CASE_LISTING, function(idx, info) {
            //     var caseRecordSimple = new ks.CaseRecordSimple(s);
            //     caseRecordSimple.init(info);
            //     s.caseRecords.push(caseRecordSimple);
            // });
        }

        s.retrieveRecord = function(refNum) {
            //if no refNum, dont do anything
            if(refNum === null || refNum === '')
                return;

            $.ajax({
                url: API_DOMAIN + '/konsulta/case/messages',
                data: JSON.stringify({
                    caseRefNum: refNum
                }),
                contentType: 'application/json', dataType: 'json', type: 'POST', cache: false,
                success : function(data) {
                    if(data) {
                        //TODO not sure if needed to do this kind of checking? just worried that there will be some replacement happening when we click send message ???
                        if (s.selectedRefNum() === refNum && s.selectedRecord() && s.selectedRecord().messages()
                            && data.messages && s.selectedRecord().messages().length === data.messages.length) {
                            var clientMessages = s.selectedRecord().messages();
                            var serverMessages = data.messages;
                        } else {
                            var caseRecordInfo = new ks.CaseRecordInfo(s.selectedRecord() ? s.selectedRecord().newMessage() : null);
                            caseRecordInfo.init(data);
                            s.selectedRecord(caseRecordInfo);  //replace only when there's changes in messages
                            s.selectedRefNum(refNum);
                        }
                    }
                }
            });

            //harcoded code
            // if(!s.selectedRecord() || s.selectedRecord().messages().length != CASE_RECORD.messages.length) {
            //     var caseRecordInfo = new ks.CaseRecordInfo();
            //     caseRecordInfo.init(CASE_RECORD);
            //     s.selectedRecord(caseRecordInfo);  //replace only when there's changes in messages
            // }
        }

        s.retrieveUpdates = function() {
            s.retrieveRecord(s.selectedRefNum());
            setTimeout(function(){ s.retrieveUpdates(); }, 2000);  //retrieve updates every 2 sec ?
        }

        s.retrieveUpdates();  //starts the long pulling
    };

    ks.CaseRecordSimple = function(parent) {
        var s = this;

        s.parent = parent;

        s.caseRefNum = ko.observable('');
        s.name = ko.observable();

        s.init = function(data) {
            s.caseRefNum(data.caseRefNum);
            s.name(data.name);
        }

        s.isSelected = function() {
            if(s.parent.selectedRecord()) {
                return s.caseRefNum() == s.parent.selectedRecord().caseRefNum();
            }
            return false;
        }
    }

    ks.CaseRecordInfo = function(nm) {
        var s = this;

        s.caseRefNum = ko.observable('');
        s.name = ko.observable('');
        s.mobileNum = ko.observable('');
        s.address = ko.observable('');

        s.messages = ko.observableArray([]);

        s.newMessage = ko.observable(nm ? nm : '');

        s.historiesData = [
            {
                "itemRefNum" : "[ID:FB6C21] Medium | Done | Triage",
                "createdDate" : "October 4, 2019",
                "summary" : "Maria's son has a fever. Confirmed with Maria that she has a thermometer, and her son has a mild fever of 37.7C." +
                    " Told her to take paracetamol and check in with us again."
            },
            {
                "itemRefNum" : "[ID:76CEA9] Low | Done | Query",
                "createdDate" : "October 2, 2019",
                "summary" : "Maria wanted to ask about trusted brands of medicine for the common cold. " +
                    "Educated patient on generic drugs and their safety and affordability compared to other popular brands."
            },
            {
                "itemRefNum" : "[ID:76CEA9] Low | Done | Query",
                "createdDate" : "September 30, 2019",
                "summary" : "Maria first reached out for a triage consultation, but was too shy to continue."
            }
        ];
        s.histories = ko.observableArray([]);


        s.init = function(data) {
            s.name(data.name);
            s.caseRefNum(data.caseRefNum);
            s.mobileNum(data.mobileNum);
            s.address(data.address);
            s.histories([]);
            // s.newMessage('');

            if(data.messages) {
                var tmpList = [];
                $.each(data.messages, function(idx, msg) {
                    var message = new ks.CaseRecordMessage();
                    message.init(msg);
                    tmpList.push(message);
                });
                s.messages(tmpList);

                setTimeout(function(){ s.scrollTop(); }, 100);
            }


            $.each(s.historiesData, function(idx, item) {
                var historyItem = new ks.CaseRecordHistoryItem();
                historyItem.init(item);
                s.histories.push(historyItem);

            });
        };

        s.sendMessage = function() {
            var message = new ks.CaseRecordMessage();
            message.init({
                "message": s.newMessage(),
                "fromPatient" : false,
                "dateSent" : moment().format('h:mm A') + ' | Today'
                    // new Date().toLocaleTimeString().toUpperCase().replace(/([\d]+:[\d]+):[\d]+(\s\w+)/g, "$1$2") + ' | ' +  "Today"
            });
            s.messages.push(message);

            $.ajax({
                url: API_DOMAIN + '/konsulta/case/message/send',
                data: JSON.stringify({
                    caseRefNum: s.caseRefNum(),
                    message: s.newMessage(),
                    staffName: staffName
                }),
                contentType: 'application/json', dataType: 'json', type: 'POST', cache: false,
                success : function(data) {}
            });

            s.newMessage('');

            setTimeout(function(){ s.scrollTop(); }, 100);

        }

        s.scrollTop = function() {
            $("#messages-div").scrollTop($("#messages-div")[0].scrollHeight);
        }
    };


    ks.CaseRecordMessage = function() {
        var s = this;
        s.id = ko.observable('');
        s.channel = ko.observable('');
        s.message = ko.observable('');
        s.fromPatient = ko.observable(false);
        s.dateSent = ko.observable('');
        s.channelIcon = ko.computed(function() {
            if(s.channel() === 'Viber') {
                return "/images/viber.png";
            }

            if(s.channel() === 'Sms') {
                return "/images/sms-icon.png";
            }

            if(s.channel() === 'WhatsApp') {
                return "/images/whatsapp-icon.png"
            }
            return "/images/sms-icon.png";

        }, this);

        s.init = function(data) {
            s.id(data.id);
            s.channel(data.channel);
            s.message(data.message ? data.message : '/blank/');
            s.fromPatient(data.fromPatient);
            s.dateSent(data.dateSent);
        }
    }

    ks.CaseRecordHistoryItem = function() {
        var s = this;
        s.itemRefNum = ko.observable('');
        s.createdDate = ko.observable('');
        s.summary = ko.observable('');

        s.init = function(data) {
            s.itemRefNum(data.itemRefNum);
            s.createdDate(data.createdDate);
            s.summary(data.summary);
        }
    }

})(window.ks = window.ks || {}, jQuery);