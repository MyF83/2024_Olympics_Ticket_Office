/***************************************************************************************************
 * BROWSER POLYFILLS
 */

/**
 * This file includes polyfills needed by Angular and is loaded before the app.
 * You can add your own extra polyfills to this file.
 *
 * The current setup is for "evergreen" browsers; the last versions of browsers that
 * automatically update themselves. This includes Safari >= 10, Chrome >= 55 (including Opera),
 * Edge >= 13 on the desktop, and iOS 10 and Chrome on mobile.
 *
 * Learn more in https://angular.io/guide/browser-support
 */

/***************************************************************************************************
 * Zone JS is required by Angular itself.
 */
import 'zone.js'; // Included with Angular CLI.

/***************************************************************************************************
 * APPLICATION IMPORTS
 */

/**
 * Add polyfills for missing features in older browsers.
 */

// Polyfill for `fetch` API (if needed)
import 'whatwg-fetch';

// Polyfill for `Promise` (if needed)
import 'core-js/es/promise';

// Polyfill for `Array.prototype.includes` (if needed)
import 'core-js/es/array/includes';

// Polyfill for `Object.entries` (if needed)
import 'core-js/es/object/entries';

// Polyfill for `Intl` (if needed)
import 'intl';
import 'intl/locale-data/jsonp/en';
