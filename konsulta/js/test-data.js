var CASE_LISTING = [
    {
        "caseRefNum": "C-191006-YZDIN",
        "name" : 'Maria'
    },
    {
        "caseRefNum": "C-191002-AZL9B",
        "name" : "Jose A"
    },
    {
        "caseRefNum": "C-191001-LMNDI",
        "name" : "Jose B"
    },
    {
        "caseRefNum": "C-191001-LKNDI",
        "name" : "Jose C"
    },
    {
        "caseRefNum": "C-191001-LMNDK",
        "name" : "Jose D"
    },
    {
        "caseRefNum": "C-191001-LXXDI",
        "name" : "Jose E"
    },
    {
        "caseRefNum": "C-191001-NKDI",
        "name" : "Jose F"
    }
];

var MESSAGES = [{
    "id" : 1,
    "channel" : "SMS",
    "message" : "I need help",
    "fromPatient": true,
    "dateSent": " 11:01 AM | June 9"
    },
    {
    "id" : 2,
    "channel" : "SMS",
    "message" : "Ok, how can I help you?",
    "fromPatient" : false,
    "dateSent": " 11:02 AM | June 9"
    },
    {
        "id" : 3,
        "channel" : "Viber",
        "message" : "I have fever",
        "fromPatient" : true,
        "dateSent": " 11:02 AM | June 9"
    },
    {
        "id" : 4,
        "channel" : "Viber",
        "message" : "Can you explain more?",
        "fromPatient" : false,
        "dateSent": " 11:02 AM | June 9"
    },
    {
        "id" : 5,
        "channel" : "Viber",
        "message" : "I have colds",
        "fromPatient" : true,
        "dateSent": " 11:02 AM | June 9"
    },
    {
        "id" : 6,
        "channel" : "Viber",
        "message" : "I have runny nose",
        "fromPatient" : true,
        "dateSent": " 11:02 AM | June 9"
    },
    {
        "id" : 7,
        "channel" : "Viber",
        "message" : "I have diarreha",
        "fromPatient" : true,
        "dateSent": " 11:02 AM | June 9"
    },
    {
        "id" : 8,
        "channel" : "WhatsApp",
        "message" : "I have high fever 2",
        "fromPatient" : true,
        "dateSent": " 11:02 AM | June 9"
    },
    {
        "id" : 9,
        "channel" : "Viber",
        "message" : "I have high fever",
        "fromPatient" : true,
        "dateSent": " 11:02 AM | June 9"
    }
];

var CASE_RECORD = {
    "caseRefNum" : "C-191006-YZDIN",
    "name" : "Maria",
    "mobileNum" : "+63 90200000",
    "address" : "54 Brg Sta Maria, Cebu City",
    "messages" : MESSAGES
};