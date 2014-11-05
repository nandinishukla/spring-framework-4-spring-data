<html>
  <head>
    <link href='http://fonts.googleapis.com/
		    css?family=Open+Sans:300italic,
		    400italic,600italic,700italic,
		    800italic,400,300,600,700,800' 
		    rel='stylesheet' type='text/css'>
    <style>
		
      body {
	    font-family:'Open sans';
      }

      .table {
	    display:table;
	    table-layout:fixed; 
      }

      .table-cell {
	    display:table-cell;
	    padding:5px;
      }

      .heading .table-cell {
	    background:#404040;
	    color:#f9f9f9;
      }

      .alt .table-cell {
	    background:#e5e5e5;
      }

      .table-row {
	    display:table-row;
	    border-right:1px solid #e5e5e5;
	    border-top:1px solid #e5e5e5;
      }
			
    </style>
    <script src="http://code.jquery.com
		/jquery-latest.min.js" 
		type="text/javascript"></script>
    <script>
		
      $(document).ready(function(){
				
        var fetchQuotesByTickerAndNumberOfDays = 
          function(ticker, numberOfDays) {
					
            $.ajax({
              type: "GET",
              url: "fetch-by-ticker",
              data: { 
                ticker: ticker,
                numberOfDays: numberOfDays 
              }
            }).done(function(response) {
						
              console.log(response);
						
              $('.quote-list').html("");
						
              $('.quote-list').append("<p>The stock " + 
                "quote history for your chosen ticker " + 
                "is shown below:</p>");
						
              $('.quote-list').append("<div class='table'>"); 
						
              $('.quote-list').append("<div class='table-row heading'>" + 
                "<div class='table-cell'>Date</div>" + 
                "<div class='table-cell'>Open</div>" + 
                "<div class='table-cell'>Close</div>" + 
                "<div class='table-cell'>High</div>" + 
                "<div class='table-cell'>Low</div></div>");
						
              for(var i=0; i<response.quotes.length; i++) {
							
                var alt = "";
                if(i % 2) {
                  alt = "alt";
                }
							
                var date = new Date(response.quotes[i].date);
							
                var dateStr =  date.getDate() + '/' + 
                  (date.getMonth() + 1) + '/' +  date.getFullYear();
							
                var quoteHtml = "<div class='table-row "+ alt +"'>" + 
                  "<div class='table-cell'>" + 
                    dateStr + "</div>" + 
                  "<div class='table-cell'>" + 
                    response.quotes[i].open + "</div>" + 
                  "<div class='table-cell'>" + 
                    response.quotes[i].close + "</div>" + 
                  "<div class='table-cell'>" + 
                    response.quotes[i].high + "</div>" + 
                  "<div class='table-cell'>" +
                    response.quotes[i].low + "</div></div>";
								
                $('.quote-list').append(quoteHtml);
							
              }
						
            }).fail(function(error) {
						
              console.log(error.status);
              console.log(error.statusText);
              console.log(error.responseText);
						
            });
					
          };
				
          var fetchLatestQuotes = function() {
					
          $.ajax({
            type: "GET",
            url: "fetch-latest",
            data: { }
          }).done(function(response) {
						
            console.log(response);
						
            $('.stock-list').html("<div class='table'>"); 
						
            $('.stock-list').append("<div class='table-row heading'>" + 
              "<div class='table-cell'>Ticker</div>" + 
              "<div class='table-cell'>Name</div>" + 
              "<div class='table-cell'>Industry</div>" + 
              "<div class='table-cell'>Sector</div>" + 
              "<div class='table-cell'>Open</div>" + 
              "<div class='table-cell'>Close</div>" + 
              "<div class='table-cell'>High</div>" + 
              "<div class='table-cell'>Low</div></div>");
						
            for(var i=0; i<response.quotes.length; i++) {
							
              var alt = "";
              if(i % 2) {
                alt = "alt";
              }
							
              var quoteHtml = "<div class='table-row "+ alt +"'>" + 
                "<div class='table-cell'><a href='' class='ticker-link'>" 
                  + response.quotes[i].ticker + "</a></div>" + 
                "<div class='table-cell'>" + 
                  response.quotes[i].name + "</div>" + 
                "<div class='table-cell'>" + 
                  response.quotes[i].industry + "</div>" + 
                "<div class='table-cell'>" + 
                  response.quotes[i].sector + "</div>" + 
                "<div class='table-cell'>" + 
                  response.quotes[i].open + "</div>" + 
                "<div class='table-cell'>" + 
                  response.quotes[i].close + "</div>" + 
                "<div class='table-cell'>" + 
                  response.quotes[i].high + "</div>" + 
                "<div class='table-cell'>" + 
                  response.quotes[i].low + "</div></div>";
								
              $('.stock-list').append(quoteHtml);
							
            }
						
            $('.ticker-link').click(function(e) {
							
              e.stopPropagation();
              e.preventDefault();
							
              var ticker = $(this).html();
							
              fetchQuotesByTickerAndNumberOfDays(ticker, 3);
							
            });
						
            $('.stock-list').append("</div>");
						
          }).fail(function(error) {
						
            console.log(error.status);
            console.log(error.statusText);
            console.log(error.responseText);
						
          });
					
        };
				
        fetchLatestQuotes();
				
      });
		
    </script>
  </head>
  <body>
    <h1>Stock Screener</h1>
    <p>Welcome to this basic stock screening application! Select a stock below to view its historical prices:</p>
    <div class="stock-list"></div>
    <div class="quote-list"></div>
  </body>
</html>