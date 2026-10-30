# AmazonDemoPractice

🚀 Workflow Steps (Assignment Requirements)
The automation follows a structured sequence across 8 functional modules:

1. Launch & Search
Maximizes the browser window and navigates to [https://www.amazon.in/](https://www.amazon.in/).

Types "Mobile" into the search bar (#twotabsearchtextbox).

Clicks the search button (#nav-search-submit-button).

2. Rating Filter
Locates the Customer Review filter in the left sidebar.

Applies the 4 Stars & Up rating filter using a centered scroll and event click to avoid sticky header obstructions.

3. Price Filter
Dynamically attaches the price boundary parameters (&low-price=10000&high-price=20000) to the current search URL.

Refreshes to load smartphones within the ₹10,000 to ₹20,000 range.

4. Sort Results
Interacts with the sort dropdown (#s-result-sort-select).

Selects the desired sort strategy (e.g., "Price: Low to High").

5. Product Selection & Window Handling
Captures the initial parent window handle (driver.getWindowHandle()).

Locates and clicks the first available sorted product result ((//div[@data-component-type='s-search-result']//h2)[1]).

Because Amazon opens product detail pages with target="_blank", explicit synchronization (wait.until(numberOfWindowsToBe(2))) waits for the new window/tab to open.

Iterates over all active window handles and switches driver focus to the child tab.

6. Add to Cart & Confirmation
Centers the viewport onto the Add to Cart button (#add-to-cart-button) and triggers the addition.

Synchronizes on the slide-out tray / confirmation message (#attachDisplayAddBaseAlert or "Added to Cart").

7. Shopping Cart Verification
Navigates directly to the official cart page: [https://www.amazon.in/gp/cart/view.html](https://www.amazon.in/gp/cart/view.html).

Asserts that the product title is displayed inside the cart items list.

Verifies that the item quantity defaults to 1.

Extracts and logs the cart subtotal amount (#sc-subtotal-amount-activecart).

8. Checkout Gate
Clicks the primary checkout action button (name="proceedToRetailCheckout").

Confirms redirection to the Amazon Sign-In / Checkout gate (validates URL containing signin or the presence of #ap_email).

Performs a clean session teardown using driver.quit().
